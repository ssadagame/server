package com.sincheon.ssadagame.infrastructure.client.steam

import com.sincheon.ssadagame.domain.game.model.Game
import com.sincheon.ssadagame.domain.game.model.GameDetail
import com.sincheon.ssadagame.domain.game.model.Genre
import com.sincheon.ssadagame.domain.game.model.PriceInfo
import com.sincheon.ssadagame.domain.game.model.PriceInfo.Companion.getOnlyNumber
import com.sincheon.ssadagame.domain.game.model.Screenshot
import org.jsoup.Jsoup

object SteamMapper {
    fun getTopSellingGames(html: String): List<Game> {
        val elements = Jsoup.parse(html).select("a")
        return elements.mapNotNull {
            runCatching {
                val childElement = it.select(" > div.responsive_search_name_combined")
                val name = childElement.select("> div.search_name > span")[0].text()
                val prices = childElement.select("> div.search_price_discount_combined div.discount_prices")[0]
                val finalPrice = getOnlyNumber(prices.select("> div.discount_final_price").text().trim())!!
                val originalPrice = getOnlyNumber(prices.select("> div.discount_original_price").text().trim()) ?: finalPrice
                Game(
                    name = name,
                    imageUrl = "",
                    priceInfo = mutableListOf(
                        PriceInfo(
                            provider = PriceInfo.Provider.STEAM,
                            price = originalPrice,
                            discountPrice = finalPrice,
                            url = it.attr("href"),
                        )
                    ),
                )
            }.getOrNull()
        }
    }

    fun getGameDetail(steamAppDetailData: SteamStoreClient.SteamAppDetailData) = GameDetail(
        appId = steamAppDetailData.appId,
        koreanName = steamAppDetailData.name,
        type = GameDetail.Type.fromValue(steamAppDetailData.type)!!,
        headerImage = steamAppDetailData.headerImage,
        capsuleImage = steamAppDetailData.capsuleImage,
        screenshots = steamAppDetailData.screenshots?.slice(0..7)?.map { Screenshot(it.pathThumbnail, it.pathFull) } ?: emptyList(),
        genres = steamAppDetailData.genres?.map { Genre(it.id, it.description) } ?: emptyList(),
    )
}
