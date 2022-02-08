package com.ajcm.data.datasource

import com.ajcm.domain.entity.Verse

interface ILocalVerseDataSource {
    suspend fun saveVerses(verses: List<Verse>)
    suspend fun saveVerse(verse: Verse)

    suspend fun getVerses(bibleId: String, chapterId: String): List<Verse>
    suspend fun getVerse(bibleId: String, verseId: String): Verse
}
