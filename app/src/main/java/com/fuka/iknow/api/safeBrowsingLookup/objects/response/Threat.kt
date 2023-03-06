package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Threat(
    @JsonProperty("url") val url: String
)