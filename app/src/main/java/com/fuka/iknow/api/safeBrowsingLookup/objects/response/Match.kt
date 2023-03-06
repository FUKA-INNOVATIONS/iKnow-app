package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fuka.iknow.api.safeBrowsingLookup.objects.request.Client
import java.net.URL
import java.time.Duration

data class Match(
  @JsonProperty("client") val client: Client?,
  @JsonProperty("threatType") val threatType: String?,
  @JsonProperty("platformType") val platformType: String?,
  @JsonProperty("threat") val threat: Threat?,
  @JsonProperty("cacheDuration") val cacheDuration: String?,
  @JsonProperty("threatEntryType") val threatEntryType: String?,
  @JsonProperty("threatEntryMetadata") val threatEntryMetadata: List<Entries>? = listOf(),
)