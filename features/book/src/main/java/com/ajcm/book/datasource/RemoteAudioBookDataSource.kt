package com.ajcm.book.datasource

import com.ajcm.book.service.BookService
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.Book
import javax.inject.Inject

class RemoteAudioBookDataSource @Inject constructor(private val service: BookService) :
    IRemoteBookDataSource {

    override suspend fun getBooks(bibleId: String): List<Book> =
        service.getAudioBooksAsync(bibleId).await().data

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        service.getAudioBookAsync(bibleId, bookId).await().data
}
