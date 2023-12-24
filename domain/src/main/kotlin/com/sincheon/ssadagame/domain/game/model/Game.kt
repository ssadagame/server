package com.sincheon.ssadagame.domain.game.model

data class Game(
    val name: String,
    val imageUrl: String,
    // TODO: mutable 제거
    val priceInfo: MutableList<PriceInfo> = mutableListOf(),
)
