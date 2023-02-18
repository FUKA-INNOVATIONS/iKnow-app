package com.fuka.iknow

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fuka.iknow.boradcast.reciever.AirPlaneBroadcastReceiver
import com.fuka.iknow.viewModels.DatabaseViewModel


class MainActivity : ComponentActivity() {

    val viewModel: DatabaseViewModel by viewModels()

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
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        val listenToBroadcastsFromOtherApps = false
        val receiverFlags =
            if (listenToBroadcastsFromOtherApps) { ContextCompat.RECEIVER_EXPORTED }
            else { ContextCompat.RECEIVER_NOT_EXPORTED }

        ContextCompat.registerReceiver(this, br, filter, receiverFlags)
    }

    /*override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }*/
}