package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.repository.IBookRepository
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: IRemoteBookDataSource,
    private val localDataSource: ILocalBookDataSource
) : IBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> {
        if (localDataSource.getBooks(bibleId).isEmpty()) {
            val books = remoteDataSource.getBooks(bibleId)
            localDataSource.saveBooks(books)
        }

        return localDataSource.getBooks(bibleId)
    }

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        localDataSource.getBook(bibleId, bookId)

}
