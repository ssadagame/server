package com.sincheon.ssadagame.domain.game.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(
    name = "genre",
    indexes = [
        Index(name = "idx_name", columnList = "name", unique = true),
    ]
)
class GenreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @OneToMany(mappedBy = "genre")
    val gameDetails: Set<GameDetailGenreEntity>,
)
