package com.sincheon.ssadagame.infrastructure.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("swagger")
@ConstructorBinding
data class SwaggerProperties(
    val title: String,
    val description: String,
    val version: String,
)
