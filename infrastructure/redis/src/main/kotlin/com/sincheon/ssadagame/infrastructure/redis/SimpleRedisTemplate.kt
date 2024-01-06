package com.sincheon.ssadagame.infrastructure.redis

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import kotlin.reflect.KFunction

class SimpleRedisTemplate(
    redisTemplate: StringRedisTemplate,
    val objectMapper: ObjectMapper,
) {
    inline fun <reified T> get(key: String): T? {
        return valueOperations.get(key)?.let { objectMapper.readValue(it, object : TypeReference<T>() {}) }
    }

    fun set(key: String, value: Any) {
        valueOperations.set(key, objectMapper.writeValueAsString(value))
    }

    fun <T : Any> set(key: String, function: KFunction<T>, arguments: List<Any>) {
        val result = function.call(arguments)
        val params = function.parameters
            .zip(arguments)
            .joinToString(separator = ":", prefix = ":") { "${it.first}=${it.second}" }
        return set(key + params, result)
    }

    val valueOperations: ValueOperations<String, String> = redisTemplate.opsForValue()

    companion object RedisKey {
        const val getTopSellingGames = "getTopSellingGames"

        fun String.addKey(key: Any, value: Any) = "$this&$key=$value"
    }
}
