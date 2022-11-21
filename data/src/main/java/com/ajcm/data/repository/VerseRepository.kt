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
        var verses = localDataSource.getVerses(bibleId, chapterId)
        if (verses.isEmpty()) {
            localDataSource.saveVerses(remoteDataSource.getVerses(bibleId, chapterId))
            verses = localDataSource.getVerses(bibleId, chapterId)
        }

        var containChanges = false
        verses.forEach {  verse ->
            if (verse.content.isEmpty()) {
                containChanges = true
                getVerse(bibleId, verse.id)
            }
        }

        if (containChanges) verses = localDataSource.getVerses(bibleId, chapterId)

        return verses
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
