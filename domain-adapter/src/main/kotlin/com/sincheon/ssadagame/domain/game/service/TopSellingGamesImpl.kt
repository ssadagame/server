package com.sincheon.ssadagame.domain.game.service

import com.sincheon.ssadagame.domain.game.model.GamePage
import com.sincheon.ssadagame.infrastructure.redis.SimpleRedisTemplate
import com.sincheon.ssadagame.infrastructure.redis.SimpleRedisTemplate.RedisKey.addKey
import org.springframework.stereotype.Component

@Component
class TopSellingGamesImpl(
    private val simpleRedisTemplate: SimpleRedisTemplate,
) : TopSellingGames {
    override fun get(page: Int, size: Int): GamePage? {
        return simpleRedisTemplate.get<GamePage>(
            SimpleRedisTemplate.getTopSellingGames.addKey("page", page).addKey("size", size)
        )
    }

    override fun set(games: GamePage, page: Int, size: Int) {
        simpleRedisTemplate.set(
            SimpleRedisTemplate.getTopSellingGames.addKey("page", page).addKey("size", size),
            games,
        )
    }
}
