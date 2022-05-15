package com.example.logisticsmanagement.web

import com.fasterxml.jackson.annotation.JsonProperty

data class WebData(
    @JsonProperty("data") val data: String
)