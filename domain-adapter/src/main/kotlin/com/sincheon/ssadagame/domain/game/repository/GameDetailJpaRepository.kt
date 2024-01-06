package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.model.GameDetailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GameDetailJpaRepository : JpaRepository<GameDetailEntity, Long> {
    fun findByAppId(appId: Long): GameDetailEntity?

    fun existsByAppId(appId: Long): Boolean
}
