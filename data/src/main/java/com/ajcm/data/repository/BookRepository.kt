package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: IRemoteBookDataSource,
    private val localDataSource: ILocalBookDataSource
) : IBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> {
        var books = localDataSource.getBooks(bibleId)
        if (books.isEmpty()) {
            localDataSource.saveBooks(remoteDataSource.getBooks(bibleId))
            books = localDataSource.getBooks(bibleId)
        }
        return books
    }

    override suspend fun getBook(bibleId: String, bookId: String): Book {
        return try {
            localDataSource.getBook(bibleId, bookId)
        } catch (e: Exception) {
            localDataSource.saveBook(remoteDataSource.getBook(bibleId, bookId))
            localDataSource.getBook(bibleId, bookId)
        }
    }
}
