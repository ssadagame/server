package com.sincheon.ssadagame.infrastructure.client.steam

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamStoreClient {
    @GET("/search/results/?query&dynamic_data=&sort_by=_ASC&category1=998&snr=1_7_7_7000_7&hidef2p=1&filter=topsellers&infinite=1")
    fun getTopSellingGames(@Query("start") start: Int, @Query("count") count: Int): Call<TopSellingGamesResponse>

    @GET("/api/appdetails/?l=korean&cc=kr")
    fun getSteamAppDetail(@Query("appids") appId: Long): Call<Map<String, SteamAppDetailResponse>>

    @GET("/api/appdetails/?l=korean&cc=kr&filters=price_overview")
    fun getSteamAppPrice(@Query("appids") appId: Long): Call<Map<String, SteamAppDetailResponse>>

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class TopSellingGamesResponse(
        val start: Int,
        val totalCount: Int,
        val success: Int,
        val resultsHtml: String,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class SteamAppDetailResponse(
        val success: Boolean,
        val data: SteamAppDetailData,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class SteamAppDetailData(
        val name: String,
        val type: String,
        @JsonProperty("steam_appid")
        val appId: Long,
        val headerImage: String,
        val capsuleImage: String,
        val screenshots: List<Screenshot>? = null,
        val genres: List<Genre>? = null,
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Screenshot(
            val id: Long,
            val pathThumbnail: String,
            val pathFull: String,
        )

        data class Genre(
            val id: Long,
            val description: String,
        )
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class SteamAppPriceResponse(
        val success: Boolean,
        val data: SteamAppPriceData,
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class SteamAppPriceData(
            val priceOverview: PriceOverview,
        ) {
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
            data class PriceOverview(
                val currency: String,
                val initial: Int,
                val final: Int,
                val discountPercent: Int,
            )
        }
    }
}
