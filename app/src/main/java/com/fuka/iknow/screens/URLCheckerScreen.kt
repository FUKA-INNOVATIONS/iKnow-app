package com.fuka.iknow.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.fuka.iknow.api.sbl_api.RetrofitHelper
import com.fuka.iknow.api.sbl_api.SafeBrowsingLookupApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun URLCheckerScreen() {
    var value by remember { mutableStateOf(TextFieldValue("")) }
    Text(text = "This is URLChecker")

    Scaffold(
        content = { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                TextField(
                    value = value,
                    onValueChange = {},
                    placeholder = "Place the URL you want to check here"
                )
            }

            // Button
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                FloatingActionButton(
                    onClick = { checkUrl(value) },
                    modifier = Modifier
                        .padding(all = 15.dp)
                ) {
                    Text("Check url")
                }
            }
        }
    )
}

@OptIn(DelicateCoroutinesApi::class)
fun checkUrl(url: TextFieldValue) {

    val urlCheckApi = RetrofitHelper.getSblInstance().create(SafeBrowsingLookupApi::class.java)

    // Launching new coroutine.
    // Should be done in normal suspend function/coroutineScope probably
    GlobalScope.launch {
        val result = urlCheckApi.sendRequest(url)
        if (result != null) {
            Log.d("Results: ", result.body().toString())
        }
    }
}