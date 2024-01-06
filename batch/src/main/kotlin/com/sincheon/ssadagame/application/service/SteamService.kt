package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.domain.game.model.GameDetail
import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.infrastructure.client.steam.SteamApiClient
import com.sincheon.ssadagame.infrastructure.client.steam.SteamMapper
import com.sincheon.ssadagame.infrastructure.client.steam.SteamStoreClient
import org.springframework.stereotype.Component

@Component
class SteamService(
    private val steamStoreClient: SteamStoreClient,
    private val steamApiClient: SteamApiClient,
) {
    fun getTopSellingGames(page: Int, size: Int): GamePage {
        if (page <= 0 || size <= 0) throw IllegalArgumentException()
        val start = (page - 1) * size
        val gamesResponse = steamStoreClient.getTopSellingGames(start, size).execute().body()!!
        val games = gamesResponse.resultsHtml.let(SteamMapper::getTopSellingGames)
        return GamePage(
            games = games,
            page = page,
            size = games.size,
            lastPage = (gamesResponse.totalCount - 1) / size + 1,
            total = gamesResponse.totalCount,
        )
    }

    fun getGameList() = steamApiClient.getSteamAppList().execute().body()!!

    fun getGameDetail(appId: Long): GameDetail? {
        val response = steamStoreClient.getSteamAppDetail(appId).execute().body()!![appId.toString()]!!
        if (response.success && response.data.type in TARGET_TYPE) {
            return response.data.let(SteamMapper::getGameDetail)
        }
        return null
    }

    companion object {
        val TARGET_TYPE = listOf("game", "dlc")
    }
}
