package com.ajcm.bible.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("api-key", apiToken)
        return chain.proceed(builder.build())
    }
}
