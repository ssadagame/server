package com.sincheon.ssadagame.infrastructure.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("client")
data class ClientProperties(
    val steam: SteamClientInfo,
    val greenmanGaming: ClientInfo,
) {
    data class SteamClientInfo(
        val storeUrl: String,
        val apiUrl: String,
    )

    data class ClientInfo(
        val serverUrl: String,
        val frontUrl: String,
    )
}
