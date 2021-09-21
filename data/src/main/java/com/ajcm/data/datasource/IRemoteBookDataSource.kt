package com.ajcm.data.datasource

import com.ajcm.domain.Book

/***
 * Used for both Book and AudioBook
 */
interface IRemoteBookDataSource {
    suspend fun getBooks(bibleId: String): List<Book>
    suspend fun getBook(bibleId: String, bookId: String): Book
}
