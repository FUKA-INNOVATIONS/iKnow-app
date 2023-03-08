package com.fuka.iknow.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.fuka.iknow.R
import com.fuka.iknow.api.safeBrowsingLookup.RetrofitClient
import com.fuka.iknow.api.safeBrowsingLookup.SafeBrowsingLookupApi
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.Client
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.LookupObject
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.ThreatEntry
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.ThreatInfo
import com.fuka.iknow.api.safeBrowsingLookup.objects.response.ResponseModel
import com.fuka.iknow.boradcast.reciever.TAG
import com.fuka.iknow.ui.theme.IKnowTheme
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun URLCheckerScreen() {
    var urlValue by remember { mutableStateOf("") }
    var matchCount by remember { mutableStateOf(0) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.secure)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    IKnowTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //Text(text = matchCount.toString())
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(400.dp)
            )

            TextField(
                value = urlValue,
                onValueChange = { urlValue = it },
                label = { Text("Place the URL you want to check here") }
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                checkUrl(urlValue) { responseCount ->
                    Log.d(TAG, "RESPONSE COUNT LAMBDA -> $responseCount")
                    responseCount.let { matchCount = responseCount!! }
                }
                Log.d(TAG, "matchCount COUNT onClick -> $matchCount")
            }) {
                Text(text = "Check url", textAlign = TextAlign.Center)
            }
        }
    }
}

// This function is given the user's input url.
// Makes api request with the url address.
@OptIn(DelicateCoroutinesApi::class)
fun checkUrl(urlString: String, countStateHandler: (Int?) -> Unit) {

    // UrlCheckApi instance.
    val urlCheckApi = RetrofitClient.getClient().create(SafeBrowsingLookupApi::class.java)

    // New ThreatEntry with user's url.
    val threatEntryObject = ThreatEntry(url = urlString)

    // New list of ThreatEntry objects.
    val threatEntryObjectList: List<ThreatEntry> = mutableListOf(threatEntryObject)

    // New ThreatInfo object including user's url.
    val newThreatInfoObject = ThreatInfo(threatEntries = threatEntryObjectList)

    // New LookupObject and api request with the said object.
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    try {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            Log.d(TAG, "111111111")
            val result = urlCheckApi
                .makeUrlCheck(
                    LookupObject(
                        client = Client(),
                        threatInfo = newThreatInfoObject
                    )
                ).execute()
            Log.d(TAG, "result -> ${result.body()?.matches?.size}")
            countStateHandler(result.body()?.matches?.size)
        }
    } catch (e: java.lang.Exception) {
        Log.d(TAG, "EX: $e")
    }

}