package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.model.GameDetail

interface GameDetailRepository {
    fun findByAppId(appId: Long): GameDetail?

    fun existsByAppId(appId: Long): Boolean

    fun save(gameDetail: GameDetail): GameDetail
}
