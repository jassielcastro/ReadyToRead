package com.ajcm.bibles.service

import com.ajcm.bibles.service.models.AudioBibleResponse
import com.ajcm.bibles.service.models.BibleResponse
import com.ajcm.bibles.service.models.BiblesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleService {
    @GET("/v1/bibles")
    fun getBiblesAsync(): Deferred<BiblesResponse>

    @GET("/v1/audio-bibles")
    fun getAudioBiblesAsync(): Deferred<BiblesResponse>

    @GET("/v1/bibles/{bibleId}")
    fun getBibleAsync(@Path("bibleId") bibleId: String): Deferred<BibleResponse>

    @GET("/v1/audio-bibles/{audioBibleId}")
    fun getAudioBibleAsync(@Path("audioBibleId") audioBibleId: String): Deferred<AudioBibleResponse>
}
