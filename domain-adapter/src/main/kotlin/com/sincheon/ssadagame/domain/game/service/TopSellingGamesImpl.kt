package com.sincheon.ssadagame.domain.game.service

import com.sincheon.ssadagame.domain.game.model.Game
import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.infrastructure.redis.SimpleRedisTemplate
import com.sincheon.ssadagame.infrastructure.redis.SimpleRedisTemplate.RedisKey.addKey
import org.springframework.stereotype.Component

@Component
class TopSellingGamesImpl(
    private val simpleRedisTemplate: SimpleRedisTemplate,
) : TopSellingGames {
    override fun get(page: Int, size: Int): GamePage? {
        val games = simpleRedisTemplate.get<List<Game>>(
            SimpleRedisTemplate.getTopSellingGames.addKey("page", page).addKey("size", size)
        )
        return games?.let {
            GamePage(
                games = it,
                page = page,
                size = it.size,
                lastPage = null,
                total = null,
            )
        }
    }

    override fun set(games: GamePage, page: Int, size: Int) {
        simpleRedisTemplate.set(
            SimpleRedisTemplate.getTopSellingGames.addKey("page", page).addKey("size", size),
            games,
        )
    }
}
