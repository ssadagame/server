package com.sincheon.ssadagame.infrastructure.client.greenmangaming

import com.sincheon.ssadagame.infrastructure.client.greenmangaming.request.SearchResultsRequest
import com.sincheon.ssadagame.infrastructure.client.greenmangaming.response.SearchResultsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GreenmanGamingClient {
    @POST("/1/indexes/*/queries?x-algolia-api-key=3bc4cebab2aa8cddab9e9a3cfad5aef3&x-algolia-application-id=SCZIZSP09Z")
    fun searchResults(@Body body: SearchResultsRequest): Call<SearchResultsResponse>
}
