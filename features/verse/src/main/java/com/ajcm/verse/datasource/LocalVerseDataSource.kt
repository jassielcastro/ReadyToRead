package com.ajcm.verse.datasource

import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
import com.ajcm.data.datasource.ILocalVerseDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Verse
import com.ajcm.verse.database.VerseDAO
import com.ajcm.verse.database.model.VerseDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalVerseDataSource @Inject constructor(
    private val verseDAO: VerseDAO,
    private val verseMapper: BaseMapper<Verse, VerseDTO>,
    @MainScope
    private val mainScope: CoroutineScope,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ILocalVerseDataSource {

    override suspend fun saveVerses(verses: List<Verse>) {
        mainScope.launch {
            withContext(ioDispatcher) {
                verseDAO.insertVerses(verses.map { verseMapper.to(it) })
            }
        }
    }

    override suspend fun saveVerse(verse: Verse) {
        mainScope.launch {
            withContext(ioDispatcher) {
                verseDAO.insertOrUpdateVerse(verseMapper.to(verse))
            }
        }
    }

    override suspend fun getVerses(bibleId: String, chapterId: String): List<Verse> {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val verses = withContext(ioDispatcher) {
                    verseDAO.getVersesByChapter(bibleId, chapterId).map { verseMapper.from(it) }
                }
                continuation.resume(verses)
            }
        }
    }

    override suspend fun getVerse(bibleId: String, verseId: String): Verse {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val verse = withContext(ioDispatcher) {
                    verseMapper.from(verseDAO.getVerse(verseId, bibleId))
                }
                continuation.resume(verse)
            }
        }
    }

}
