package com.ajcm.book.service

import com.ajcm.domain.entity.Book
import com.ajcm.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("/v1/bibles/{bibleId}/books")
    suspend fun getBooksAsync(@Path("bibleId") bibleId: String): Response<List<Book>>

    @GET("/v1/bibles/{bibleId}/books/{bookId}")
    suspend fun getBookAsync(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Response<Book>

    @GET("/v1/audio-bibles/{bibleId}/books")
    suspend fun getAudioBooksAsync(@Path("bibleId") bibleId: String): Response<List<Book>>

    @GET("/v1/audio-bibles/{bibleId}/books/{bookId}")
    suspend fun getAudioBookAsync(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Response<Book>

}
