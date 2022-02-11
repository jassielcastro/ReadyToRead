package com.ajcm.chapter.service

import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterService {

    @GET("/v1/bibles/{bibleId}/books/{bookId}/chapters")
    suspend fun getChapters(
        @Path("bibleId") bibleId: String,
        @Path("bookId") bookId: String
    ): Response<List<Chapter>>

    @GET("/v1/bibles/{bibleId}/chapters/{chapterId}?content-type=text&include-notes=true&include-titles=true&include-chapter-numbers=true")
    suspend fun getChapter(
        @Path("bibleId") bibleId: String,
        @Path("chapterId") chapterId: String
    ): Response<Chapter>

}
