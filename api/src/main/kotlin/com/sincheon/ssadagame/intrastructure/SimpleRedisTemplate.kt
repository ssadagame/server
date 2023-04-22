package com.sincheon.ssadagame.intrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class SimpleRedisTemplate(
    redisTemplate: StringRedisTemplate,
    val objectMapper: ObjectMapper,
) {
    final inline fun <reified T> get(key: String): T? {
        return valueOperations.get(key)?.let { objectMapper.readValue(it, T::class.java) }
    }

    fun set(key: String, value: Any) {
        valueOperations.set(key, objectMapper.writeValueAsString(value))
    }

    val valueOperations = redisTemplate.opsForValue()
}
