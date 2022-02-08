package com.ajcm.bibles.service

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleService {
    @GET("/v1/bibles")
    suspend fun getBibles(): Response<List<Bible>>

    @GET("/v1/bibles/{bibleId}")
    suspend fun getBible(@Path("bibleId") bibleId: String): Response<Bible>

}
