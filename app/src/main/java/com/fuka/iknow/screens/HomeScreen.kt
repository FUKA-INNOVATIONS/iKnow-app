package com.fuka.iknow.screens

import android.app.Application
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fuka.iknow.viewModels.DatabaseViewModel
import com.fuka.iknow.viewModels.TAG


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel = DatabaseViewModel(context.applicationContext as Application)
    val broadcastActionList = viewModel.getBroadcastActions().observeAsState(listOf())
    Scaffold(
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 15.dp)
            ) {
                items(broadcastActionList.value) {

                    /* var statusColor by remember { // change list item status icon color based on status: either true (unchecked/red) or false (checked/blue)
                        mutableStateOf(if (it.status) Color.Gray else Color.LightGray)
                    } */

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(padding)
                    ) {
                        ListItem(
                            headlineText = { Text("Airplane mode changed") },
                            overlineText = { Text(it.type) },
                            supportingText = { Text(it.timestamp) },
                            leadingContent = {
                                Icon(
                                    Icons.Filled.CheckCircle,
                                    contentDescription = "Airplane mode event item status icon",
                                    tint = if (it.status) Color.Gray else Color.LightGray, // TODO: list item is not updated on status change
                                    modifier = Modifier.clickable {
                                        Log.d(TAG, "it.hashCode: ${it.intentHashcode.toString()}")
                                        viewModel.changeBroadcastActionStatus(it)
                                    }
                                )
                            },
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