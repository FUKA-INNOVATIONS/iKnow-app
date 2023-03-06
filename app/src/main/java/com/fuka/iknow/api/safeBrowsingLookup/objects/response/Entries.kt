package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty

data class Entries(
  @JsonProperty("entries") val entries: List<Entry> = listOf()
)
