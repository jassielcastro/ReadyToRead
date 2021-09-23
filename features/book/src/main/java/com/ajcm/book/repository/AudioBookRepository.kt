package com.ajcm.book.repository

import com.ajcm.data.datasource.IRemoteAudioBookDataSource
import com.ajcm.data.repository.IAudioBookRepository
import com.ajcm.domain.Book
import javax.inject.Inject

class AudioBookRepository @Inject constructor(private val dataSource: IRemoteAudioBookDataSource) :
    IAudioBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> = dataSource.getBooks(bibleId)

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        dataSource.getBook(bibleId, bookId)
}
