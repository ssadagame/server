package com.sincheon.ssadagame.infrastructure.client.steam

import com.sincheon.ssadagame.domain.Game
import com.sincheon.ssadagame.domain.PriceInfo
import com.sincheon.ssadagame.domain.PriceInfo.Companion.getOnlyNumber
import org.jsoup.Jsoup

object SteamMapper {
    fun getTopSellingGames(html: String): List<Game> {
        val elements = Jsoup.parse(html).select("a")
        return elements.map {
            val childElement = it.select(" > div.responsive_search_name_combined")
            val name = childElement.select("> div.col.search_name.ellipsis > span")[0].text()
            val prices = childElement.select("> div.col.search_price_discount_combined.responsive_secondrow > div.col.search_price.responsive_secondrow")[0]
                .text().replace("""[,₩]\s?""".toRegex(), "").split(" ")
            Game(
                name,
                mutableListOf(
                    PriceInfo(
                        provider = PriceInfo.Provider.STEAM,
                        price = getOnlyNumber(prices.first().trim()),
                        discountPrice = getOnlyNumber(prices.last().trim()),
                        url = it.attr("href"),
                    )
                )
            )
        }
    }
}
