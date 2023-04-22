package com.sincheon.ssadagame.infrastructure.client.greenmangaming.response

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchResultsResponse(
    val results: List<Result>,
) {
    data class Result(
        val hits: List<Hit>,
    ) {
        data class Hit(
            @JsonProperty("Url")
            val url: String,
            @JsonProperty("Regions")
            val region: Region,
            @JsonProperty("DisplayName")
            val displayName: String,
        ) {
            data class Region(
                @JsonProperty("KR")
                val kr: GameInfo,
            ) {
                data class GameInfo(
                    @JsonProperty("Rrp")
                    val rrp: Int,
                    @JsonProperty("Drp")
                    val drp: Int,
                    @JsonProperty("IsOnSale")
                    val isOnSale: Boolean,
                )
            }
        }
    }
}
