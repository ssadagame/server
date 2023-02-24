package com.sincheon.ssadagame.application.model

import com.sincheon.ssadagame.domain.Game

data class GamePage(
    val games: List<Game>,
    val page: Int,
    val size: Int,
    val lastPage: Int? = null,
    val total: Int? = null,
)
