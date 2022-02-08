package com.ajcm.data.datasource

import com.ajcm.domain.entity.Book

/***
 * Used for both Book
 */
interface IRemoteBookDataSource {
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
