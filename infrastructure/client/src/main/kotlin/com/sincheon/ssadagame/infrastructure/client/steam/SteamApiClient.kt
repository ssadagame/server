package com.sincheon.ssadagame.infrastructure.client.steam

import com.fasterxml.jackson.annotation.JsonProperty
import retrofit2.Call
import retrofit2.http.GET

interface SteamApiClient {
    @GET("/ISteamApps/GetAppList/v2/?format=json")
    fun getSteamAppList(): Call<SteamAppListResponse>

    data class SteamAppListResponse(
        @JsonProperty("applist")
        val appList: SteamApps,
    )

    data class SteamApps(
        val apps: List<SteamApp>,
    )

    data class SteamApp(
        @JsonProperty("appid")
        val appId: Long,
        val name: String,
    )
}
