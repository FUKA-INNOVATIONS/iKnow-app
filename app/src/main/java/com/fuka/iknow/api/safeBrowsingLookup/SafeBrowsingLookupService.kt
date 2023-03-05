package com.fuka.iknow.api.safeBrowsingLookup

import com.fasterxml.jackson.databind.ObjectMapper
import com.fuka.iknow.api.safeBrowsingLookup.Objects.ErrorResponse
import com.fuka.iknow.api.safeBrowsingLookup.Objects.LookupObject
import okhttp3.ResponseBody

class SafeBrowsingLookupService {

  /**
   * TODO: Could get error if : is not after Content-Type in response header.
   */

  private val retrofit = RetrofitClient.getClient()
  private val safeBrowsingLookupApi = retrofit.create(SafeBrowsingLookupApi::class.java)

  fun successfulSafeBrowsingLookupResponse() {
    val safeBrowsingResponse = safeBrowsingLookupApi.makeUrlCheck()
      .execute()

    val successful = safeBrowsingResponse.isSuccessful
    val httpStatusCode = safeBrowsingResponse.code()
    val httpStatusMessage = safeBrowsingResponse.message()

    // If response is successful the .body() is populated.
    // Otherwise the .errorBody() is not null.
    val body: LookupObject? = safeBrowsingResponse.body()
    val errorBody: ResponseBody? = safeBrowsingResponse.errorBody()

    val mapper = ObjectMapper()

    val mappedBody: ErrorResponse? = errorBody?.let { notNullErrorBody ->
      mapper.readValue(notNullErrorBody.toString(), ErrorResponse::class.java)
    }

    val headers = safeBrowsingResponse.headers()
    val customHeaderValue = headers["Content-Type"]
  }
}