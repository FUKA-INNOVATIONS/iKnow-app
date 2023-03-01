package com.fuka.iknow

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.core.content.ContextCompat
import com.fuka.iknow.boradcast.reciever.AirPlaneBroadcastReceiver
import com.fuka.iknow.navigation.NavigationPage
import com.fuka.iknow.service.BroadcastActionNotificationService
import com.fuka.iknow.viewModels.BroadcastActionViewModel
import com.fuka.iknow.viewModels.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: DatabaseViewModel by viewModels()
    val viewModel_v2: BroadcastActionViewModel by viewModels()
    // @Inject lateinit var viewModel_v2: BroadcastActionViewModel

    private lateinit var br: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationPage(viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        br = AirPlaneBroadcastReceiver()

        val filterAirplane = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val filterCameraButton = IntentFilter(Intent.ACTION_CAMERA_BUTTON)
        val filterCALL = IntentFilter(Intent.ACTION_CALL)
        val filterUserUnlocked = IntentFilter(Intent.ACTION_USER_UNLOCKED)

        val listenToBroadcastsFromOtherApps = false
        val receiverFlags =
            if (listenToBroadcastsFromOtherApps) { ContextCompat.RECEIVER_EXPORTED }
            else { ContextCompat.RECEIVER_NOT_EXPORTED }

        ContextCompat.registerReceiver(this, br, filterAirplane, receiverFlags)

    }

    /*override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }*/

}
