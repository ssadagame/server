package com.sincheon.ssadagame.intrastructure.client.greenmangaming

import com.sincheon.ssadagame.domain.PriceInfo
import com.sincheon.ssadagame.intrastructure.client.greenmangaming.response.SearchResultsResponse
import com.sincheon.ssadagame.intrastructure.config.properties.ClientProperties
import org.springframework.stereotype.Component

@Component
class GreenmanGamingMapper(
    private val clientProperties: ClientProperties,
) {
    fun searchResults(response: SearchResultsResponse, name: String): PriceInfo? =
        response.results[0].hits
            .find { it.displayName.lowercase().replace(Regex("[^a-z0-9]"), "") == name.lowercase().replace(Regex("[^a-z0-9]"), "") }
            ?.let {
                PriceInfo(
                    provider = PriceInfo.Provider.GREENMANGAMING,
                    url = "${clientProperties.greenmanGaming.frontUrl}${it.url}",
                    price = it.region.kr.rrp.toString(),
                    discountPrice = it.region.kr.drp.toString(),
                )
            }
}
