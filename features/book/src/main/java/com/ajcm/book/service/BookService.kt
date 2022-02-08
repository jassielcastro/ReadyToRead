package com.ajcm.book.service

import com.ajcm.domain.entity.Book
import com.ajcm.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("/v1/bibles/{bibleId}/books")
    suspend fun getBooks(@Path("bibleId") bibleId: String): Response<List<Book>>

    @GET("/v1/bibles/{bibleId}/books/{bookId}")
    suspend fun getBook(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Response<Book>

}
