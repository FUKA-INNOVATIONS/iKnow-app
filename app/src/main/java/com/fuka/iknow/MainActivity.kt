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
import com.fuka.iknow.navigation.NavigationPage
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

        val filterAirplane = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val filterCameraButton = IntentFilter(Intent.ACTION_CAMERA_BUTTON)
        val filterCALL = IntentFilter(Intent.ACTION_CALL)
        val filterUserUnlocked = IntentFilter(Intent.ACTION_USER_UNLOCKED)

        val listenToBroadcastsFromOtherApps = false
        val receiverFlags =
            if (listenToBroadcastsFromOtherApps) { ContextCompat.RECEIVER_EXPORTED }
            else { ContextCompat.RECEIVER_NOT_EXPORTED }

        ContextCompat.registerReceiver(this, br, filterAirplane, receiverFlags)
        //ContextCompat.registerReceiver(this, br, filterCameraButton, receiverFlags)
        //ContextCompat.registerReceiver(this, br, filterCALL, receiverFlags)
        //ContextCompat.registerReceiver(this, br, filterUserUnlocked, receiverFlags)
    }

    /*override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }*/

}


@Composable
fun MainAppNav(viewModel: DatabaseViewModel) {
    IKnowTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppBody(viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBody(viewModel: DatabaseViewModel) {
    val broadcastActionList = viewModel.getBroadcastActions().observeAsState(listOf())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("iKnow")
                }
            )
        },

        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                items(broadcastActionList.value) {
                    // Content here

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(padding)
                    ) {
                        Card(Modifier.size(width = 300.dp, height = 110.dp)) {
                            Text(
                                text = "Type: ${it.type}",
                                // style = ...
                            )
                            Text(
                                text = "Value: ${it.value}",
                                // style = ...
                            )
                            Text(
                                text = "Time recorded: ${it.timestamp}"
                                // style = ...
                            )
                        }
                    }
                }
            }

            // Floating action button
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                FloatingActionButton(
                    onClick = { viewModel.addBroadcastActions("dummyAction", "dummyType") },
                    modifier = Modifier
                        .padding(all = 15.dp)
                ) {
                    Text("Add more!")
                }
            }
        }
    )

}