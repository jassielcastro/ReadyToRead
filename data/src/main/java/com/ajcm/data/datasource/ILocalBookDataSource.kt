package com.ajcm.data.datasource

import com.ajcm.domain.Book

interface ILocalBookDataSource {
    suspend fun saveBooks(books: List<Book>)
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
