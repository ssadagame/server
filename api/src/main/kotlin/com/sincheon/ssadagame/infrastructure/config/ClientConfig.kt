package com.sincheon.ssadagame.infrastructure.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sincheon.ssadagame.infrastructure.client.HTMLInterceptor
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.GreenmanGamingClient
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.GreenmanGamingMapper
import com.sincheon.ssadagame.infrastructure.client.steam.SteamApiClient
import com.sincheon.ssadagame.infrastructure.client.steam.SteamHTMLClient
import com.sincheon.ssadagame.infrastructure.client.steam.SteamStoreClient
import com.sincheon.ssadagame.infrastructure.config.properties.ClientProperties
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class ClientConfig(
    private val clientProperties: ClientProperties,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun steamHTMLClient(): SteamHTMLClient = retrofit()
        .baseUrl(clientProperties.steam.storeUrl)
        .client(clientForHTML())
        .build()
        .create(SteamHTMLClient::class.java)

    @Bean
    fun steamStoreClient(): SteamStoreClient = retrofit()
        .baseUrl(clientProperties.steam.storeUrl)
        .build()
        .create(SteamStoreClient::class.java)

    @Bean
    fun steamApiClient(): SteamApiClient = retrofit()
        .baseUrl(clientProperties.steam.apiUrl)
        .build()
        .create(SteamApiClient::class.java)

    @Bean
    fun greenmanGamingClient(): GreenmanGamingClient = retrofit()
        .baseUrl(clientProperties.greenmanGaming.serverUrl)
        .build()
        .create(GreenmanGamingClient::class.java)

    @Bean
    fun greenmanGamingMapper() = GreenmanGamingMapper(clientProperties.greenmanGaming.frontUrl)

    private fun retrofit() =
        Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create(objectMapper))

    private fun clientForHTML() =
        OkHttpClient().newBuilder().addInterceptor(HTMLInterceptor).build()
}
