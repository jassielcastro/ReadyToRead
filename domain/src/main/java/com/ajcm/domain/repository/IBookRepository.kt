package com.ajcm.domain.repository

import com.ajcm.domain.entity.Book

interface IBookRepository {
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
