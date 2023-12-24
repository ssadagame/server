package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.domain.game.service.TopSellingGames
import org.springframework.stereotype.Service

@Service
class GameService(
    private val topSellingGames: TopSellingGames,
) {
    fun getTopSellingGames(page: Int, size: Int): GamePage {
        return topSellingGames.get(page, size) ?: throw IllegalStateException()
    }
}
