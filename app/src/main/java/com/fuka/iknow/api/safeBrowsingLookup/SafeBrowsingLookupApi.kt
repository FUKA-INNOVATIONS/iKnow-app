package com.fuka.iknow.api.safeBrowsingLookup

import com.fuka.iknow.api.safeBrowsingLookup.Objects.LookupObject
import retrofit2.Call
import retrofit2.http.POST

interface SafeBrowsingLookupApi {

  @POST
  fun makeUrlCheck(): Call<LookupObject>

}