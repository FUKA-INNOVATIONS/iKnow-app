package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseModel(
  @JsonProperty("matches") val matches: List<Match>? = listOf()
)
