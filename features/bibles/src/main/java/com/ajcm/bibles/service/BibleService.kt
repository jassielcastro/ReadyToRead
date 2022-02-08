package com.ajcm.bibles.service

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleService {
    @GET("/v1/bibles")
    suspend fun getBiblesAsync(): Response<List<Bible>>

    @GET("/v1/audio-bibles")
    suspend fun getAudioBiblesAsync(): Response<List<Bible>>

    @GET("/v1/bibles/{bibleId}")
    suspend fun getBibleAsync(@Path("bibleId") bibleId: String): Response<Bible>

    @GET("/v1/audio-bibles/{audioBibleId}")
    suspend fun getAudioBibleAsync(@Path("audioBibleId") audioBibleId: String): Response<AudioBible>
}
