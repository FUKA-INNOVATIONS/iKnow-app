package com.fuka.iknow.api.safeBrowsingLookup.objects.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ThreatEntry(
  //@JsonProperty("hash") val hash: String,
  @JsonProperty("url") val url: String,
  //@JsonProperty("digest") val digest: String,
)
