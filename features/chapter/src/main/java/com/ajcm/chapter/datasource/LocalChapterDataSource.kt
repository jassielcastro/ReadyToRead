package com.ajcm.chapter.datasource

import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Chapter
import com.ajcm.chapter.database.ChapterDAO
import com.ajcm.chapter.database.model.ChapterDTO
import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalChapterDataSource @Inject constructor(
    private val chapterDAO: ChapterDAO,
    private val chapterMapper: BaseMapper<Chapter, ChapterDTO>,
    @MainScope
    private val mainScope: CoroutineScope,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ILocalChapterDataSource {

    override suspend fun saveChapters(chapters: List<Chapter>) {
        mainScope.launch {
            withContext(ioDispatcher) {
                chapterDAO.insertChapters(chapters.map { chapterMapper.to(it) })
            }
        }
    }

    override suspend fun saveChapter(chapter: Chapter) {
        mainScope.launch {
            withContext(ioDispatcher) {
                chapterDAO.insertOrUpdateChapter(chapterMapper.to(chapter))
            }
        }
    }

    override suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val chapters = withContext(ioDispatcher) {
                    chapterDAO.getChapterByBook(bibleId, bookId).map { chapterMapper.from(it) }
                }
                continuation.resume(chapters)
            }
        }
    }

    override suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val chapter = withContext(ioDispatcher) {
                    chapterMapper.from(chapterDAO.getChapter(chapterId, bibleId))
                }
                continuation.resume(chapter)
            }
        }
    }

}
