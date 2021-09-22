package com.ajcm.book.repository

import com.ajcm.annotation.AudioBookDataSource
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.data.repository.IBookRepository
import com.ajcm.domain.Book
import javax.inject.Inject

class AudioBookRepository @Inject constructor(@AudioBookDataSource private val dataSource: IRemoteBookDataSource) :
    IBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> = dataSource.getBooks(bibleId)

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        dataSource.getBook(bibleId, bookId)
}
