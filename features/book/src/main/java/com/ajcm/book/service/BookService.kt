package com.ajcm.book.service

import com.ajcm.domain.Book
import com.ajcm.domain.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("/v1/bibles/{bibleId}/books")
    fun getBooksAsync(@Path("bibleId") bibleId: String): Deferred<Response<List<Book>>>

    @GET("/v1/bibles/{bibleId}/books/{bookId}")
    fun getBookAsync(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Deferred<Response<Book>>

    @GET("/v1/audio-bibles/{bibleId}/books")
    fun getAudioBooksAsync(@Path("bibleId") bibleId: String): Deferred<Response<List<Book>>>

    @GET("/v1/audio-bibles/{bibleId}/books/{bookId}")
    fun getAudioBookAsync(@Path("bibleId") bibleId: String, @Path("bookId") bookId: String): Deferred<Response<Book>>

}
