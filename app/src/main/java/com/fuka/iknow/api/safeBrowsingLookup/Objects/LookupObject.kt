package com.fuka.iknow.api.safeBrowsingLookup.Objects

import com.fasterxml.jackson.annotation.JsonProperty

data class LookupObject(
  @JsonProperty("client") val client: Client,
  @JsonProperty("threatInfo") val threatInfo: ThreatInfo,
)