package com.sincheon.ssadagame.domain.game.service

import com.sincheon.ssadagame.domain.game.model.GamePage

interface TopSellingGames {
    fun get(page: Int, size: Int): GamePage?
    fun set(games: GamePage, page: Int, size: Int)
}
