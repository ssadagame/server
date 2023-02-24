package com.sincheon.ssadagame.intrastructure.client.greenmangaming.request

import java.net.URLEncoder

data class SearchResultsRequest(
    val requests: List<Request>,
) {
    data class Request(
        val indexName: String = "prod_ProductSearch_GS_KR",
        val params: String,
    ) {
        companion object {
            fun toParams(query: String) = query.let {
                "query=${URLEncoder.encode(it, "UTF-8")}&ruleContexts=%5B%22KRW%22%2C%22KRW_KR%22%2C%22KR%22%5D&filters=IsSellable%3Atrue%20AND%20AvailableRegions%3AKR%20AND%20NOT%20ExcludeCountryCodes%3AKR%20AND%20IsDlc%3Afalse&hitsPerPage=24&distinct=true&analytics=false&clickAnalytics=false&maxValuesPerFacet=10&highlightPreTag=__ais-highlight__&highlightPostTag=__%2Fais-highlight__&page=0&tagFilters=&facetFilters=%5B%5B%22DrmName%3ASteam%22%5D%5D"
            }
        }
    }
}
