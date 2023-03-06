package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL
import java.time.Duration

data class Match(
  @JsonProperty("threatType") val threatType: String,
  @JsonProperty("platformType") val platformType: String,
  @JsonProperty("threatEntryType") val threatEntryType: String,
  @JsonProperty("threat") val threat: URL,
  @JsonProperty("threatEntryMetadata") val threatEntryMetadata: List<Entries> = listOf(),
  @JsonProperty("cacheDuration") val cacheDuration: Duration,
)