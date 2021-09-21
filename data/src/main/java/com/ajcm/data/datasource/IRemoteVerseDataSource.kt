package com.ajcm.data.datasource

import com.ajcm.domain.Verse
import com.ajcm.domain.VerseSummary

interface IRemoteVerseDataSource {
    suspend fun getVerses(bibleId: String, chapterId: String): List<VerseSummary>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
