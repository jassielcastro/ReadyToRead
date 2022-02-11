package com.ajcm.book.datasource

import com.ajcm.book.database.BookDAO
import com.ajcm.book.database.model.BookDTO
import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalBookDataSource @Inject constructor(
    private val bookDAO: BookDAO,
    private val bookMapper: BaseMapper<Book, BookDTO>,
    @MainScope
    private val mainScope: CoroutineScope,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ILocalBookDataSource {

    override suspend fun saveBooks(books: List<Book>) {
        mainScope.launch {
            withContext(ioDispatcher) {
                bookDAO.insertBooks(books.map { bookMapper.to(it) })
            }
        }
    }

    override suspend fun saveBook(book: Book) {
        mainScope.launch {
            withContext(ioDispatcher) {
                bookDAO.insertOrUpdateBook(bookMapper.to(book))
            }
        }
    }

    override suspend fun getBooks(bibleId: String): List<Book> {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val books = withContext(ioDispatcher) {
                    bookDAO.getAllBooksByBible(bibleId).map { bookMapper.from(it) }
                }
                continuation.resume(books)
            }
        }
    }

    override suspend fun getBook(bibleId: String, bookId: String): Book {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val book = withContext(ioDispatcher) {
                    bookMapper.from(bookDAO.getBook(bookId, bibleId))
                }
                continuation.resume(book)
            }
        }
    }

}
