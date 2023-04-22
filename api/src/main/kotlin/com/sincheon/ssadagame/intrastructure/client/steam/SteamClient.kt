package com.sincheon.ssadagame.intrastructure.client.steam

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamClient {
    @GET("/search/results/?query&dynamic_data=&sort_by=_ASC&category1=998&snr=1_7_7_7000_7&hidef2p=1&filter=topsellers&infinite=1")
    fun getTopSellingGames(@Query("start") start: Int, @Query("count") count: Int): Call<TopSellingGamesResponse>

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class TopSellingGamesResponse(
        val start: Int,
        val totalCount: Int,
        val success: Int,
        val resultsHtml: String,
    )
}
