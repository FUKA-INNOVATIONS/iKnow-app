package com.fuka.iknow.api.safeBrowsingLookup.Objects

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
  @JsonProperty("message") val message: String
)
