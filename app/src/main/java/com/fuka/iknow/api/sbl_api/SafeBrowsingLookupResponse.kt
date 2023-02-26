package com.fuka.iknow.api.sbl_api

import java.time.Duration


data class SafeBrowsingLookupResponse(
    val threatTypes: List<String>,
    val platformTypes: List<String>,
    val threatEntryTypes: List<String>,
    val threat: List<String>,
    val threatEntryMetadata: Metadata,
    val cacheDuration: Duration,
)