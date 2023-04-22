package com.sincheon.ssadagame.domain

data class Game(
    val name: String,
    val priceInfo: MutableList<PriceInfo> = mutableListOf(),
)
