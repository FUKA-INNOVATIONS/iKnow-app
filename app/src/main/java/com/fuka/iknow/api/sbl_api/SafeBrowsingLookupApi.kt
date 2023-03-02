package com.fuka.iknow.api.sbl_api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SafeBrowsingLookupApi {

    // Post
    //@FormUrlEncoded
    @POST("https://safebrowsing.googleapis.com/v4/threatMatches:find")
    suspend fun sendRequest(
        @HeaderMap headers: Map<String, String>
    ) : Response<LookupObject>

    companion object {
        private const val BASE_URL = "qwerty"
        val safeBrowsingLookupApi: SafeBrowsingLookupApi by lazy {
            Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(SafeBrowsingLookupApi::class.java)
        }
    }

}