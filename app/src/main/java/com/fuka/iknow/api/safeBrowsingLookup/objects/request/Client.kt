package com.fuka.iknow.api.safeBrowsingLookup.objects.request

import com.fasterxml.jackson.annotation.JsonProperty

data class Client(
  @JsonProperty("clientId") val clientId: String = "iknow",
  @JsonProperty("clientVersion") val clientVersion: String = "1.7.0",
)
