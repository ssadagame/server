package com.sincheon.ssadagame.infrastructure.config

import io.lettuce.core.ReadFrom.REPLICA_PREFERRED
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfig {
    @Bean
    fun redisConnectionFactory(
        @Value("\${redis.host}") host: String,
        @Value("\${redis.port}") port: Int,
    ) =
        LettuceConnectionFactory(
            RedisStaticMasterReplicaConfiguration(host, port),
            LettuceClientConfiguration.builder().readFrom(REPLICA_PREFERRED).build()
        )

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory) =
        StringRedisTemplate(redisConnectionFactory)
}
