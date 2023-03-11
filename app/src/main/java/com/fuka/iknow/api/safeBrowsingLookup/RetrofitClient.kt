package com.fuka.iknow.api.safeBrowsingLookup

import com.fuka.iknow.BuildConfig
import com.fuka.iknow.api.safeBrowsingLookup.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object RetrofitClient {

  private val BASE_URL: String = "https://safebrowsing.googleapis.com/v4/threatMatches:find/"

  // OkHttpClient
  // Consists all of the Interceptors
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

