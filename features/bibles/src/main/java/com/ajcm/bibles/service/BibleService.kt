package com.ajcm.bibles.service

import com.ajcm.domain.AudioBible
import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary
import com.ajcm.domain.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleService {
    @GET("/v1/bibles")
    fun getBiblesAsync(): Deferred<Response<List<BibleSummary>>>

    @GET("/v1/audio-bibles")
    fun getAudioBiblesAsync(): Deferred<Response<List<BibleSummary>>>

    @GET("/v1/bibles/{bibleId}")
    fun getBibleAsync(@Path("bibleId") bibleId: String): Deferred<Response<Bible>>

    @GET("/v1/audio-bibles/{audioBibleId}")
    fun getAudioBibleAsync(@Path("audioBibleId") audioBibleId: String): Deferred<Response<AudioBible>>
}
