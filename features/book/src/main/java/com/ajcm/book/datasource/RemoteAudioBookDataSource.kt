package com.ajcm.book.datasource

import com.ajcm.book.service.BookService
import com.ajcm.data.datasource.IRemoteAudioBookDataSource
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class RemoteAudioBookDataSource @Inject constructor(private val service: BookService) :
    IRemoteAudioBookDataSource {

    override suspend fun getBooks(bibleId: String): List<Book> =
        service.getAudioBooksAsync(bibleId).data

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        service.getAudioBookAsync(bibleId, bookId).data
}
