package com.fuka.iknow.api.safeBrowsingLookup

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.Client
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.LookupObject
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.ThreatInfo
import com.fuka.iknow.api.safeBrowsingLookup.objects.response.ErrorResponse
import com.fuka.iknow.api.safeBrowsingLookup.objects.response.ResponseModel
import com.fuka.iknow.boradcast.reciever.TAG
import okhttp3.ResponseBody

class SafeBrowsingLookupService {

  /**
   * TODO: Could get error if : is not after Content-Type in response header.
   */

  private val retrofit = RetrofitClient.getClient()
  private val safeBrowsingLookupApi = retrofit.create(SafeBrowsingLookupApi::class.java)

  fun successfulSafeBrowsingLookupResponse() {
    Log.d(TAG, " successfulSafeBrowsingLookupResponse 1")
    val safeBrowsingResponse = safeBrowsingLookupApi
      .makeUrlCheck(
        lookupObject = LookupObject(
          client = Client(),
          threatInfo = ThreatInfo()
        )
      )
      .execute()

    Log.d(TAG, " successfulSafeBrowsingLookupResponse 2")

    Log.d(TAG, " safeBrowsingResponse ${safeBrowsingResponse}")

    val successful = safeBrowsingResponse.isSuccessful
    val httpStatusCode = safeBrowsingResponse.code()
    val httpStatusMessage = safeBrowsingResponse.message()

    // If response is successful the .body() is populated.
    // Otherwise the .errorBody() is not null.
    val body: ResponseModel? = safeBrowsingResponse.body()
    val errorBody: ResponseBody? = safeBrowsingResponse.errorBody()

    // When Retrofit populates errorBody we use .let function to place the content into ErrorResponse instance.
    val mapper = ObjectMapper()
    val mappedBody: ErrorResponse? = errorBody?.let { notNullErrorBody ->
      mapper.readValue(notNullErrorBody.toString(), ErrorResponse::class.java)
    }

    // Obtains response header value.
    val headers = safeBrowsingResponse.headers()
    val customHeaderValue = headers["Content-Type"]
  }
}