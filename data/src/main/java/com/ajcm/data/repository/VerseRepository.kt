package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalVerseDataSource
import com.ajcm.data.datasource.IRemoteVerseDataSource
import com.ajcm.domain.entity.Verse
import com.ajcm.domain.repository.IVerseRepository
import javax.inject.Inject

class VerseRepository @Inject constructor(
    private val localDataSource: ILocalVerseDataSource,
    private val remoteDataSource: IRemoteVerseDataSource
) : IVerseRepository {

    override suspend fun getVerses(bibleId: String, chapterId: String): List<Verse> {
        if (localDataSource.getVerses(bibleId, chapterId).isEmpty()) {
            val verses = remoteDataSource.getVerses(bibleId, chapterId)
            localDataSource.saveVerses(verses)
        }

        return localDataSource.getVerses(bibleId, chapterId)
    }

    override suspend fun getVerse(bibleId: String, verseId: String): Verse {
        val verse = try {
            localDataSource.getVerse(bibleId, verseId)
        } catch (e: Exception) {
            remoteDataSource.getVerse(bibleId, verseId)
        }

        if (verse.content.isEmpty()) {
            localDataSource.saveVerse(remoteDataSource.getVerse(bibleId, verseId))
        }

        return localDataSource.getVerse(bibleId, verseId)
    }

}
