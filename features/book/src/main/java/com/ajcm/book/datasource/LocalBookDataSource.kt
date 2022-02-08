package com.ajcm.book.datasource

import com.ajcm.book.database.BookDAO
import com.ajcm.book.database.model.BookDTO
import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class LocalBookDataSource @Inject constructor(
    private val bookDAO: BookDAO,
    private val bookMapper: BaseMapper<Book, BookDTO>
) : ILocalBookDataSource {

    override suspend fun saveBooks(books: List<Book>) {
        bookDAO.insertBooks(books.map { bookMapper.to(it) })
    }

    override suspend fun saveBook(book: Book) {
        bookDAO.insertOrUpdateBook(bookMapper.to(book))
    }

    override suspend fun getBooks(bibleId: String): List<Book> {
        return bookDAO.getAllBooksByBible(bibleId).map { bookMapper.from(it) }
    }

    override suspend fun getBook(bibleId: String, bookId: String): Book {
        return bookMapper.from(bookDAO.getBook(bookId, bibleId))
    }

}
