package com.sincheon.ssadagame.infrastructure.client.greenmangaming

import com.sincheon.ssadagame.domain.game.model.PriceInfo
import com.sincheon.ssadagame.domain.game.model.PriceInfo.Companion.getOnlyNumber
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.response.SearchResultsResponse

class GreenmanGamingMapper(
    private val frontUrl: String,
) {
    fun searchResults(response: SearchResultsResponse, name: String): PriceInfo? =
        response.results[0].hits
            .find { it.displayName.lowercase().replace(Regex("[^a-z0-9]"), "") == name.lowercase().replace(Regex("[^a-z0-9]"), "") }
            ?.let {
                PriceInfo(
                    provider = PriceInfo.Provider.GREENMANGAMING,
                    url = "$frontUrl${it.url}",
                    price = getOnlyNumber(it.region.kr.rrp.toString()),
                    discountPrice = getOnlyNumber(it.region.kr.drp.toString()),
                )
            }
}
