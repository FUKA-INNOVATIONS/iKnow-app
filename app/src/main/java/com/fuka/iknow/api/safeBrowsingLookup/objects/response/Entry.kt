package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Entry(
  @JsonProperty("key") val key: String,
  @JsonProperty("value") val value: String
)
