package com.fuka.iknow.api.safeBrowsingLookup.objects.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
  @JsonProperty("message") val message: String
)
