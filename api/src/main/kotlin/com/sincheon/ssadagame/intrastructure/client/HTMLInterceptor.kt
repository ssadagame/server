package com.sincheon.ssadagame.intrastructure.client

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

object HTMLInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.isSuccessful && response.body != null) {
            val json = JSONObject()
            json.put("data", response.body!!.string())
            return response.newBuilder()
                .body(json.toString().toResponseBody("application/json; charset=utf-8".toMediaType()))
                .build()
        } else {
            throw Exception()
        }
    }
}
