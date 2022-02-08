package com.kavak.verse.datasource

import com.ajcm.data.datasource.IRemoteVerseDataSource
import com.ajcm.domain.entity.Verse
import com.kavak.verse.service.VerseService
import javax.inject.Inject

class RemoteVerseDataSource @Inject constructor(
    private val verseService: VerseService
) : IRemoteVerseDataSource {

    override suspend fun getVerses(bibleId: String, chapterId: String): List<Verse> {
        return verseService.getVerses(bibleId, chapterId).data
    }

    override suspend fun getVerse(bibleId: String, verseId: String): Verse {
        return verseService.getVerse(bibleId, verseId).data
    }

}
