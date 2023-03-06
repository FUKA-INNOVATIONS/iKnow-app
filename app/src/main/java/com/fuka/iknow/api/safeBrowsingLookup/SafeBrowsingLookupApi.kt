package com.fuka.iknow.api.safeBrowsingLookup

import com.fuka.iknow.BuildConfig
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.LookupObject
import com.fuka.iknow.api.safeBrowsingLookup.objects.response.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SafeBrowsingLookupApi {

  @Headers("Content-Type: application/json")
  @POST("?key=${BuildConfig.SBL_API_KEY}") // HTTP/1.1/
  fun makeUrlCheck(
    @Body lookupObject: LookupObject
  ): Call<ResponseModel>

}