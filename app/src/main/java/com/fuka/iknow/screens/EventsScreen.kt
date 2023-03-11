package com.fuka.iknow.screens

import CustomDropdownMenu
import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fuka.iknow.viewModels.DatabaseViewModel
import com.fuka.iknow.viewModels.TAG


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel = DatabaseViewModel(context.applicationContext as Application)
    var broadcastActionList = viewModel.getBroadcastActions().observeAsState(listOf())
    var batteryLevelList = viewModel.getBroadcastActionsByType("batteryLow").observeAsState(listOf())
    var airplaneModeList = viewModel.getBroadcastActionsByType("airplaneMode").observeAsState(listOf())
    var wifiStateList = viewModel.getBroadcastActionsByType("WifiStateChanged").observeAsState(listOf())

    var activeList by remember { mutableStateOf(broadcastActionList) }

    val airplaneBtnActive = if (activeList == airplaneModeList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val batteryBtnActive = if (activeList == batteryLevelList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val wifiBtnActive = if (activeList == wifiStateList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary
    val showAlBtnActive = if (activeList == broadcastActionList) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.onPrimary


    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { activeList = airplaneModeList }, colors = ButtonDefaults.buttonColors(contentColor = airplaneBtnActive)) {
                Text(text = "Airplane")
            }

            OutlinedButton(onClick = { activeList = batteryLevelList }, colors = ButtonDefaults.buttonColors(contentColor = batteryBtnActive)) {
                Text(text = "Battery")
            }
            OutlinedButton(onClick = { activeList = wifiStateList }, colors = ButtonDefaults.buttonColors(contentColor = wifiBtnActive)) {
                Text(text = "Wifi state")
            }

            OutlinedButton(onClick = { activeList = broadcastActionList }, colors = ButtonDefaults.buttonColors(contentColor = showAlBtnActive)) {
                Text(text = "All")
            }
        }

        //Row(modifier = Modifier.height(50.dp)) { CustomDropdownMenu(eventTitles = listOf<String>("First", "Second", "Third")) }


        Scaffold(
            content = { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    items(activeList.value) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            ListItem(
                                //headlineText = { Text("Airplane mode changed") },
                                headlineText = { Text(it.type) },
                                //overlineText = { Text(it.type) },
                                supportingText = { Text(it.timestamp) },
                                /*leadingContent = {
                                    Icon(
                                        Icons.Filled.CheckCircle,
                                        contentDescription = "Event item status icon",
                                        tint = if (it.status) Color.Gray else Color.LightGray, // TODO: list item is not updated on status change
                                        modifier = Modifier.clickable {
                                            Log.d(TAG, "it.hashCode: ${it.intentHashcode.toString()}")
                                            viewModel.changeBroadcastActionStatus(it)
                                        }
                                    )
                                },*/
                                trailingContent = {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Delete Airplane mode event icon",
                                        tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.clickable {
                                            viewModel.deleteBroadcastAction(it)
                                            Log.d("iKnow-app", it.status.toString())
                                        }
                                    )
                                }
                            )
                            Divider()
                        }
                    }
                }

            }
        )
    }


}