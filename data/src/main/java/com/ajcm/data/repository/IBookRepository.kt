package com.ajcm.data.repository

import com.ajcm.domain.Book

interface IBookRepository {
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
