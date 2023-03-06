package com.fuka.iknow.api.safeBrowsingLookup.objects.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class ThreatInfo(
  @JsonProperty("threatTypes") val threatTypes: List<String> = listOf("MALWARE", "SOCIAL_ENGINEERING"),
  @JsonProperty("platformTypes") val platformTypes: List<String> = listOf("WINDOWS"),
  @JsonProperty("threatEntryTypes") val threatEntryTypes: List<String> = listOf("URL"),
  @JsonProperty("threatEntries") val threatEntries: List<ThreatEntry> = mutableListOf()
)
