package com.sincheon.ssadagame.domain.game.model

import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.math.roundToInt

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PriceInfo(
    val provider: Provider,
    val url: String,
    val price: String?,
    val discountPrice: String?,
) {
    val discountRate: String?
        get() = calculateDiscountRate()

    private fun calculateDiscountRate(): String? {
        if (price == null || discountPrice == null) return null
        if (price == discountPrice || (price.isEmpty() || discountPrice.isEmpty())) return null

        val price = this.price.toDouble()
        val currentPrice = this.discountPrice.toDouble()

        return ((price - currentPrice) * 100 / price).roundToInt().toString() + "%"
    }

    companion object {
        fun getOnlyNumber(price: String) = price.replace(Regex("\\D"), "").ifEmpty { null }
    }

    enum class Provider {
        STEAM,
        GREENMANGAMING,
        ;
    }
}
