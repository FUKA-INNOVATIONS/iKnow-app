package com.fuka.iknow.api.sbl_api

import okhttp3.Interceptor
import okhttp3.Response
/*

// Tämä on huonompi tapa tehdä kuin HeaderMap

class SafeBrowsingLookupHeader : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }

}

 */