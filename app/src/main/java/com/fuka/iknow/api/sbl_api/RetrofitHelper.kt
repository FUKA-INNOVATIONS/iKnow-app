package com.fuka.iknow.api.sbl_api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Isoin osa tätä objectia on tehty seuraamalla alinta ohjetta täällä:
 * https://stackoverflow.com/questions/51832058/how-to-add-api-key-into-interceptor-using-okhttp
 *
 * Osan olen tehnyt tällä (Api kutsut header interceptor enimmäkseen)
 * https://github.com/LinkedInLearning/android-development-retrofit-with-kotlin-2882228/tree/main/app/src/main/java/com/rkpandey/blogexplorer/api
 *
 * Retrofit 2 by default leverages OkHttp as the networking layer and is built on top of it.
 */
object RetrofitHelper {
    val BASE_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find"
    //val API_KEY = BuildConfig.SBL_API_KEY

    // Onko tämä tietoturvariski?
    // val sblUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=${API_KEY} HTTP/1.1"

    fun getSblInstance(): Retrofit {

        // parempi tapa tehdä on HeaderMap object
        /*val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    // error ehkä jos : ei PODT https perässä tai content typen
                    builder.header("POST https", "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=${BuildConfig.SBL_API_KEY} HTTP/1.1")
                    builder.header("Content-Type", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()*/

        // saattaa olla turha
        val api_interceptor = Interceptor {
            val originalRequest = it.request()
            val newHttpUrl = originalRequest.url.newBuilder()
                //.addQueryParameter("app_key", API_KEY)
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()
            it.proceed(newRequest)
        }

        // Adds logs to api calls
        val logger = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        val clientHTTP = OkHttpClient().newBuilder()
            .addNetworkInterceptor(logger) // valinnanvarainen
            .addNetworkInterceptor(api_interceptor)
            .build()

        return Retrofit.Builder()
            .client(clientHTTP)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // Gson converter factory converts JSON object to Java object
            .build()
    }
}