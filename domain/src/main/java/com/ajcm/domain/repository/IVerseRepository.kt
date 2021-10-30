package com.ajcm.domain.repository

import com.ajcm.domain.entity.Verse
import com.ajcm.domain.entity.VerseSummary

interface IVerseRepository {
    suspend fun getVerses(bibleId: String, chapterId: String): List<VerseSummary>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
