package com.sincheon.ssadagame.intrastructure.client

import com.fasterxml.jackson.annotation.JsonProperty

data class HTMLResponse(
    @JsonProperty("data")
    val data: String,
)
