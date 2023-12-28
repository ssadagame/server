package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.model.GameDetailGenreEntity
import com.sincheon.ssadagame.domain.game.model.GameDetailGenreKey
import org.springframework.data.jpa.repository.JpaRepository

interface GameDetailGenreJpaRepository : JpaRepository<GameDetailGenreEntity, GameDetailGenreKey>
