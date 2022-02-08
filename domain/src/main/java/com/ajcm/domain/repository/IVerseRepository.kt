package com.ajcm.domain.repository

import com.ajcm.domain.entity.Verse

interface IVerseRepository {
    suspend fun getVerses(bibleId: String, chapterId: String): List<Verse>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
