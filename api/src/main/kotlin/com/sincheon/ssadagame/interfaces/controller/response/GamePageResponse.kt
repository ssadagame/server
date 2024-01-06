package com.sincheon.ssadagame.interfaces.controller.response

import com.sincheon.ssadagame.domain.game.model.Game

data class GamePageResponse(
    val games: List<Game>,
    val size: Int,
    val page: Int,
    val lastPage: Int?,
    val total: Int?,
)
