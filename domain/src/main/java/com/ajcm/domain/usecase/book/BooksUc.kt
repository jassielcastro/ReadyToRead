package com.ajcm.domain.usecase.book

import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import javax.inject.Inject

class BooksUc @Inject constructor(
    private val repository: IBookRepository
) {

    suspend fun getBooksOf(bibleId: String): List<Book> {
        return repository.getBooks(bibleId)
    }

    suspend fun getBook(bibleId: String, bookId: String): Book {
        return repository.getBook(bibleId, bookId)
    }
}
