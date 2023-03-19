package com.sincheon.ssadagame.intrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class SimpleRedisTemplate(
    private val redisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper,
) {
    fun get(key: String, clazz: Class<*>): Any? {
        return objectMapper.readValue(valueOperations.get(key), clazz)
    }

    fun set(key: String, value: Any) {
        valueOperations.set(key, objectMapper.writeValueAsString(value))
    }

    private val valueOperations = redisTemplate.opsForValue()
}
