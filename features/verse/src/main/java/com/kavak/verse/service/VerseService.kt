package com.kavak.verse.service

import com.ajcm.domain.entity.Response
import com.ajcm.domain.entity.Verse
import retrofit2.http.GET
import retrofit2.http.Path

interface VerseService {

    @GET("/v1/bibles/{bibleId}/chapters/{chapterId}/verses")
    suspend fun getVerses(
        @Path("bibleId") bibleId: String,
        @Path("chapterId") chapterId: String
    ): Response<List<Verse>>

    @GET("/v1/bibles/{bibleId}/verses/{verseId}?content-type=text")
    suspend fun getVerse(
        @Path("bibleId") bibleId: String,
        @Path("verseId") verseId: String
    ): Response<Verse>

}
