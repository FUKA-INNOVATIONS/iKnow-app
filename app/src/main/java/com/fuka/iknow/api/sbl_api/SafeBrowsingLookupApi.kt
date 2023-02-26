package com.fuka.iknow.api.sbl_api

import android.webkit.SafeBrowsingResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface SafeBrowsingLookupApi {

    /*
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
     */

    // Post
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("https://safebrowsing.googleapis.com/v4/threatMatches:find?key=API_KEY HTTP/1.1")
    suspend fun sendRequest(
        @Field("clientId") clientId: String,
        @Field("clientVersion") clientVersion: String,
        @Field("threatTypes") threatTypes: List<String>,
        @Field("platformTypes") platformTypes: List<String>,
        @Field("threatEntryTypes") threatEntryTypes: List<String>,
        @Field("threatEntries") threatEntries: List<String>,
    ) : Response<SafeBrowsingResponse>


    //suspend fun
    // @POST("https://safebrowsing.googleapis.com/v4/threatMatches:find?key=API_KEY HTTP/1.1")
}