package com.sincheon.ssadagame.domain.game.repository

import com.sincheon.ssadagame.domain.game.model.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreJpaRepository : JpaRepository<GenreEntity, Long> {
    fun findByName(name: String): GenreEntity?
}
