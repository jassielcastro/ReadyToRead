package com.ajcm.data.repository

import com.ajcm.domain.Verse
import com.ajcm.domain.VerseSummary

interface IVerseRepository {
    suspend fun getVerses(bibleId: String, chapterId: String): List<VerseSummary>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
