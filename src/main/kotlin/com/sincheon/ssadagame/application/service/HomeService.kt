package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.application.model.GamePage
import org.springframework.stereotype.Service

@Service
class HomeService(
    private val steamService: SteamService,
    private val greenmanGamingService: GreenmanGamingService,
) {
    fun getTopSellingGames(page: Int, size: Int): GamePage {
        val gamePage = steamService.getTopSellingGames(page, size)
        gamePage.games.forEach { game ->
            greenmanGamingService.getGamePrice(game.name)?.let { price ->
                game.priceInfo.add(price)
            }
        }
        return gamePage
    }
}
