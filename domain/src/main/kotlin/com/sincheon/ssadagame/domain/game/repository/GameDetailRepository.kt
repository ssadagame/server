package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.model.GameDetail

interface GameDetailRepository {
    fun getGameDetail(appId: Long): GameDetail?

    fun save(gameDetail: GameDetail): GameDetail
}
