package com.ajcm.data.repository

import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.repository.IBookRepository
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class BookRepository @Inject constructor(private val dataSource: IRemoteBookDataSource) :
    IBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> = dataSource.getBooks(bibleId)

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        dataSource.getBook(bibleId, bookId)
}
