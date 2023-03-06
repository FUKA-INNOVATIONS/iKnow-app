package com.fuka.iknow.api.safeBrowsingLookup.objects.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class ThreatInfo(
  @JsonProperty("threatTypes") val threatTypes: List<String> = listOf("MALWARE", "SOCIAL_ENGINEERING", "UNWANTED_SOFTWARE", "THREAT_TYPE_UNSPECIFIED", "POTENTIALLY_HARMFUL_APPLICATION"),
  @JsonProperty("platformTypes") val platformTypes: List<String> = listOf("WINDOWS", "LINUX", "ANDROID", "OSX", "IOS", "CHROME"),
  @JsonProperty("threatEntryTypes") val threatEntryTypes: List<String> = listOf("URL"),
  @JsonProperty("threatEntries") val threatEntries: List<ThreatEntry> = mutableListOf()
)
