package com.fuka.iknow.api.sbl_api

import com.fuka.iknow.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val sblUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=API_KEY HTTP/1.1"
    val key = BuildConfig.SBL_API_KEY
    // https://api.themoviedb.org/3/movie/603?api_key=YOUR_API_KEY&language=en-US

    fun getSblInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(sblUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // Gson converter factory converts JSON object to Java object
            .build()
    }
}