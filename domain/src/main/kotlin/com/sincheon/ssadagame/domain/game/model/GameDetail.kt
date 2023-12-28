package com.sincheon.ssadagame.domain.game.model

data class GameDetail(
    val id: Long,
    val appId: Long,
    val name: String,
    val type: Type,
    val headerImage: String,
    val capsuleImage: String,
    val screenshots: List<Screenshot>,
    val genres: List<Genre>,
) {
    enum class Type(val value: String) {
        GAME("game"),
        DLC("dlc"),
        ;

        companion object {
            fun fromValue(value: String): Type? {
                return values().find { it.value == value }
            }
        }
    }
}
