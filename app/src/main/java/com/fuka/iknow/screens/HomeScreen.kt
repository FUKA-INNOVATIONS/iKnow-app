package com.fuka.iknow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fuka.iknow.NavigationPage
import com.fuka.iknow.viewModels.DatabaseViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: DatabaseViewModel) {
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
                // Content here

                Spacer(modifier = Modifier.height(20.dp))

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
                onClick = { viewModel.addBroadcastActions() },
                modifier = Modifier
                    .padding(all = 15.dp)
            ) {
                Text("Add more!")
            }
        }

    }
    )
}