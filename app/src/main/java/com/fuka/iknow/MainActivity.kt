package com.fuka.iknow

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.fuka.iknow.boradcast.reciever.AirPlaneBroadcastReceiver
import com.fuka.iknow.navigation.NavigationPage
import com.fuka.iknow.ui.theme.IKnowTheme
import com.fuka.iknow.viewModels.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
