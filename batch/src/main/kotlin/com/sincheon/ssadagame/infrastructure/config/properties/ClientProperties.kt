package com.sincheon.ssadagame.infrastructure.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("client")
data class ClientProperties(
    val steam: ClientInfo,
    val greenmanGaming: ClientInfo,
) {
    data class ClientInfo(
        val serverUrl: String,
        val frontUrl: String,
    )
}
