package com.fuka.iknow.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    var matchCount by remember { mutableStateOf<Int?>(null) }

    val noSearchAnimation by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.secure)
    )

    val noSearchAnimationProgress by animateLottieCompositionAsState(
        noSearchAnimation,
        iterations = LottieConstants.IterateForever
    )

    val safeSearchAnimation by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.secure2)
    )

    val safeSearchAnimationProgress by animateLottieCompositionAsState(
        noSearchAnimation,
        iterations = 1
    )

    val notSafeSearchAnimation by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.warning)
    )

    val notSafeSearchAnimationProgress by animateLottieCompositionAsState(
        noSearchAnimation,
        iterations = 1
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {

        if (matchCount == null) { // no searches yet

            LottieAnimation(
                noSearchAnimation,
                noSearchAnimationProgress,
                modifier = Modifier.size(400.dp)
            )

        } else if (matchCount == 0) { // No matches, given URL is safe

            LottieAnimation(
                safeSearchAnimation,
                safeSearchAnimationProgress,
                modifier = Modifier.size(400.dp)
            )

        } else if (matchCount!! > 0 ) { // Found matches, given URL is not safe

            LottieAnimation(
                notSafeSearchAnimation,
                notSafeSearchAnimationProgress,
                modifier = Modifier.size(400.dp)
            )

        }

        TextField(
            modifier = Modifier.padding(15.dp),
            value = urlValue,
            onValueChange = { urlValue = it },
            label = { Text(stringResource(R.string.url_textField_label)) },
            maxLines = 1
        )



        Button(onClick = {
            checkUrl(urlValue) { responseCount ->
                Log.d(TAG, "RESPONSE COUNT LAMBDA -> $responseCount")
                //responseCount.let { matchCount = responseCount!! }
                matchCount = responseCount ?: 0
            }
            Log.d(TAG, "matchCount COUNT onClick -> $matchCount")
        }) {
            Text(text = stringResource(R.string.url_submit_button), textAlign = TextAlign.Center)
        }

        //Spacer(modifier = Modifier.size(50.dp))
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