package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.application.model.GamePage
import com.sincheon.ssadagame.intrastructure.SimpleRedisTemplate
import org.springframework.context.annotation.Primary
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

@Service
@Primary
class CachedHomeService(
    steamService: SteamService,
    greenmanGamingService: GreenmanGamingService,
    private val simpleRedisTemplate: SimpleRedisTemplate,
) : HomeService(
    steamService,
    greenmanGamingService,
) {
    override fun getTopSellingGames(page: Int, size: Int): GamePage {
        return simpleRedisTemplate.get<GamePage>(getTopSellingGames)
            ?: super.getTopSellingGames(page, size).also { simpleRedisTemplate.set(getTopSellingGames, it) }
    }

    companion object RedisKey {
        const val getTopSellingGames = "HomeService:getTopSellingGames"
    }
}
