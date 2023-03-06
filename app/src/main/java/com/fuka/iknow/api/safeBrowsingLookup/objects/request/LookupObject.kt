package com.fuka.iknow.api.safeBrowsingLookup.objects.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LookupObject(
  @JsonProperty("client") val client: Client,
  @JsonProperty("threatInfo") val threatInfo: ThreatInfo,
)