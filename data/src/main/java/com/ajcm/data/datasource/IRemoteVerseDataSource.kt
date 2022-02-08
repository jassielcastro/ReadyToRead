package com.ajcm.data.datasource

import com.ajcm.domain.entity.Verse

interface IRemoteVerseDataSource {
    suspend fun getVerses(bibleId: String, chapterId: String): List<Verse>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
