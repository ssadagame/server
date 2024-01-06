package com.sincheon.ssadagame.interfaces.controller.response

import com.sincheon.ssadagame.domain.game.model.GameDetail
import com.sincheon.ssadagame.domain.game.model.Genre
import com.sincheon.ssadagame.domain.game.model.Screenshot

data class GameDetailResponse(
    val appId: Long,
    val name: String,
    val type: GameDetail.Type,
    val headerImage: String,
    val capsuleImage: String,
    val screenshots: List<Screenshot>,
    val genres: List<Genre>,
)
