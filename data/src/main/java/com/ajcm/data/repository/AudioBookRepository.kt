package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.datasource.IRemoteAudioBookDataSource
import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IAudioBookRepository
import javax.inject.Inject

class AudioBookRepository @Inject constructor(
    private val remoteDataSource: IRemoteAudioBookDataSource,
    private val localDataSource: ILocalBookDataSource
) : IAudioBookRepository {

    override suspend fun getBooks(bibleId: String): List<Book> {
        if (localDataSource.getBooks(bibleId).isEmpty()) {
            val books = remoteDataSource.getBooks(bibleId)
            localDataSource.saveBooks(books)
        }

        return localDataSource.getBooks(bibleId)
    }

    override suspend fun getBook(bibleId: String, bookId: String): Book =
        localDataSource.getBook(bibleId, bookId)

}
