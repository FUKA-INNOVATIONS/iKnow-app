package com.fuka.iknow.api.sbl_api

import com.fuka.iknow.BuildConfig

/**
 * tein HeaderMap objektista perus funktion ja pistin suspend, ne ehkä väärin
 */

// tätä ohjetta käytin-
// https://proandroiddev.com/headers-in-retrofit-a8d71ede2f3e

suspend fun HeaderMap() {

    SafeBrowsingLookupApi.safeBrowsingLookupApi.sendRequest(getHeaderMap())
    //.subscribeOn(Schedulers.io())
    //.observeOn(AndroidSchedulers.mainThread())
    //.subscribe({ handleSuccess(it) }, { handleError(it) })

    fun getHeaderMap(): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["POST https"] =
            "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=${BuildConfig.SBL_API_KEY} HTTP/1.1"
        headerMap["Content-Type"] = "application/json"
        return headerMap
    }
}