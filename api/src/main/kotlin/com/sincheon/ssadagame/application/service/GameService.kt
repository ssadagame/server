package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.domain.game.model.Game
import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.domain.game.repository.GameDetailRepository
import com.sincheon.ssadagame.domain.game.service.TopSellingGames
import org.springframework.stereotype.Service

@Service
class GameService(
    private val topSellingGames: TopSellingGames,
    private val gameDetailRepository: GameDetailRepository,
) {
    fun getTopSellingGames(page: Int, size: Int): GamePage {
        return topSellingGames.get(page, size) ?: throw IllegalStateException()
    }

    fun getGamePrice(appId: Long): Game {
        val name = gameDetailRepository.getGameDetail(appId) ?: throw IllegalArgumentException()
        return Game(name)
    }
}
