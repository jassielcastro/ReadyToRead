package com.ajcm.domain.repository

import com.ajcm.domain.entity.Book

interface IAudioBookRepository {
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
