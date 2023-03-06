package com.fuka.iknow.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fuka.iknow.api.safeBrowsingLookup.RetrofitClient
import com.fuka.iknow.api.safeBrowsingLookup.SafeBrowsingLookupApi
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.Client
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.LookupObject
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.ThreatEntry
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.ThreatInfo
import com.fuka.iknow.api.safeBrowsingLookup.objects.response.ResponseModel
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun URLCheckerScreen() {
  var urlValue by remember { mutableStateOf("") }
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
          value = urlValue,
          onValueChange = { urlValue = it },
          label = { Text("Place the URL you want to check here") }
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
          onClick = { checkUrl(urlValue) },
          modifier = Modifier
            .padding(all = 15.dp)
        ) {
          Text("Check url")
        }
      }
    }
  )
}

// This function is given the user's input url.
// Makes api request with the url address.
@OptIn(DelicateCoroutinesApi::class)
fun checkUrl(urlString: String) {

  // UrlCheckApi instance.
  val urlCheckApi = RetrofitClient.getClient().create(SafeBrowsingLookupApi::class.java)

  // New ThreatEntry with user's url.
  val threatEntryObject = ThreatEntry(url = urlString)

  // New list of ThreatEntry objects.
  val threatEntryObjectList: List<ThreatEntry> = mutableListOf(threatEntryObject)

  // New ThreatInfo object including user's url.
  val newThreatInfoObject = ThreatInfo(threatEntries = threatEntryObjectList)

  // New LookupObject and api request with the said object.
  val result = urlCheckApi
    .makeUrlCheck(
      LookupObject(
        client = Client(),
        threatInfo = newThreatInfoObject
      )
    ).execute()

  /**
   * TODO: Pistä toimimaan ja tee coroutine versio.
   */

  // Yritän asettaa responsin ResponseModelin sisään ja tehä siitä uuden objektin.
  // Näkee että antaa Call<ResponseModel> ja haluu listan
  val resultt = result.body()?.let {
    ResponseModel(
    matches =  it.matches
    )
  }

  Log.d("Resultt in URLScreen: ", resultt.toString())
  Log.d("Resultt matches in URLScreen: ", resultt?.matches.toString())

  /*
  // Launching new coroutine.
  // Should be done in normal suspend function/coroutineScope probably
  GlobalScope.launch {
      val result = urlCheckApi.makeUrlCheck(url)
      if (result != null) {
          Log.d("Results: ", result.body().toString())
      }
  }

   */
}