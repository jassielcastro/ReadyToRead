package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    override suspend fun getBook(bibleId: String, bookId: String): Book {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    localDataSource.getBook(bibleId, bookId)
                }.onSuccess {
                    continuation.resume(it)
                }.onFailure {
                    val book = remoteDataSource.getBook(bibleId, bookId)
                    localDataSource.saveBook(book)
                    continuation.resume(localDataSource.getBook(bibleId, bookId))
                }
            }
        }
    }

}
