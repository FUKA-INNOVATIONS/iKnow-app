package com.fuka.iknow.api.safeBrowsingLookup

import com.fuka.iknow.BuildConfig
import com.fuka.iknow.api.safeBrowsingLookup.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

  //private const val BASE_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key="
  //val API_KEY = BuildConfig.SBL_API_KEY

  // Base URL pitää ehkä loppua / tai IllegalArgumentException
  private val BASE_URL = "POST https://safebrowsing.googleapis.com/v4/threatMatches:find?key=${BuildConfig.SBL_API_KEY} HTTP/1.1"

  // OkHttpClient
  // Consists all of the Interceptors and headers
  val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(RequestInterceptor)
    .build()

  // Retrofit intent
  fun getClient(): Retrofit =
    Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(BASE_URL)
      .addConverterFactory(JacksonConverterFactory.create())
      .build()

}

