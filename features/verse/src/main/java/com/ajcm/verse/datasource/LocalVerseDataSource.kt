package com.ajcm.verse.datasource

import com.ajcm.data.datasource.ILocalVerseDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Verse
import com.ajcm.verse.database.VerseDAO
import com.ajcm.verse.database.model.VerseDTO
import javax.inject.Inject

class LocalVerseDataSource @Inject constructor(
    private val verseDAO: VerseDAO,
    private val verseMapper: BaseMapper<Verse, VerseDTO>
) : ILocalVerseDataSource {

    override suspend fun saveVerses(verses: List<Verse>) {
        verseDAO.insertVerses(verses.map { verseMapper.to(it) })
    }

    override suspend fun saveVerse(verse: Verse) {
        verseDAO.insertOrUpdateVerse(verseMapper.to(verse))
    }

    override suspend fun getVerses(bibleId: String, chapterId: String): List<Verse> {
        return verseDAO.getVersesByChapter(bibleId, chapterId).map { verseMapper.from(it) }
    }

    override suspend fun getVerse(bibleId: String, verseId: String): Verse {
        return verseMapper.from(verseDAO.getVerse(verseId, bibleId))
    }

}
