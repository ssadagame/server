package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.infrastructure.client.steam.SteamClient
import com.sincheon.ssadagame.infrastructure.client.steam.SteamMapper
import org.springframework.stereotype.Component

@Component
class SteamService(
    private val steamClient: SteamClient,
) {
    fun getTopSellingGames(page: Int, size: Int): GamePage {
        if (page <= 0 || size <= 0) throw Exception()
        val start = (page - 1) * size
        val gamesResponse = steamClient.getTopSellingGames(start, size).execute().body()!!
        val games = gamesResponse.resultsHtml.let(SteamMapper::getTopSellingGames)
        return GamePage(
            games = games,
            page = page,
            size = games.size,
            lastPage = (gamesResponse.totalCount - 1) / size + 1,
            total = gamesResponse.totalCount,
        )
    }
}
