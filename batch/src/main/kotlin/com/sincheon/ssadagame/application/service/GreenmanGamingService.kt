package com.sincheon.ssadagame.application.service

import com.sincheon.ssadagame.domain.game.model.PriceInfo
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.GreenmanGamingClient
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.GreenmanGamingMapper
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.request.SearchResultsRequest
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.request.SearchResultsRequest.Request.Companion.toParams
import org.springframework.stereotype.Service

@Service
class GreenmanGamingService(
    private val greenmanGamingClient: GreenmanGamingClient,
    private val greenmanGamingMapper: GreenmanGamingMapper,
) {
    fun getGamePrice(name: String): PriceInfo? = greenmanGamingClient.searchResults(
        SearchResultsRequest(
            requests = listOf(
                SearchResultsRequest.Request(
                    params = toParams(name)
                )
            )
        )
    ).execute().body()!!
        .let {
            greenmanGamingMapper.searchResults(it, name)
        }
}
