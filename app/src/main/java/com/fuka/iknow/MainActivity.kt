package com.fuka.iknow

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.fuka.iknow.boradcast.reciever.AirPlaneBroadcastReceiver
import com.fuka.iknow.ui.theme.IKnowTheme

class MainActivity : ComponentActivity() {
    private lateinit var br: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IKnowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IKnowTheme {
        Greeting("Android")
    }
}