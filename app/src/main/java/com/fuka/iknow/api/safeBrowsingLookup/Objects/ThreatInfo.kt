package com.fuka.iknow.api.safeBrowsingLookup.Objects

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class ThreatInfo(
  @JsonProperty("threatTypes") val threatTypes: List<String> = listOf("MALWARE", "SOCIAL_ENGINEERING"),
  @JsonProperty("platformTypes") val platformTypes: List<String> = listOf("WINDOWS"),
  @JsonProperty("threatEntryTypes") val threatEntryTypes: List<String> = listOf("URL"),
  // Voi ehkä tulla jotain ongelmaa jos url on string muodossa eikä url
  @JsonProperty("threatEntries") val threatEntries: List<URL> = listOf(URL("http://www.urltocheck1.org/"))
)
