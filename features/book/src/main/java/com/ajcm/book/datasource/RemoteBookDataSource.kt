package com.ajcm.book.datasource

import com.ajcm.book.service.BookService
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class RemoteBookDataSource @Inject constructor(private val service: BookService) :
    IRemoteBookDataSource {

    override suspend fun getBooks(bibleId: String): List<Book> =
        service.getBooksAsync(bibleId).data

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        service.getBookAsync(bibleId, bookId).data
}
