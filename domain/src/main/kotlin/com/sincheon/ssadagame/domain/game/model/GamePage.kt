package com.sincheon.ssadagame.domain.game.model

data class GamePage(
    val games: List<Game>,
    val page: Int,
    val size: Int,
    val lastPage: Int? = null,
    val total: Int? = null,
)
