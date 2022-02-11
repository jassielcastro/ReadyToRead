package com.ajcm.chapter.datasource

import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Chapter
import com.ajcm.chapter.database.ChapterDAO
import com.ajcm.chapter.database.model.ChapterDTO
import javax.inject.Inject

class LocalChapterDataSource @Inject constructor(
    private val chapterDAO: ChapterDAO,
    private val chapterMapper: BaseMapper<Chapter, ChapterDTO>
) : ILocalChapterDataSource {

    override suspend fun saveChapters(chapters: List<Chapter>) {
        chapterDAO.insertChapters(chapters.map { chapterMapper.to(it) })
    }

    override suspend fun saveChapter(chapter: Chapter) {
        chapterDAO.insertOrUpdateChapter(chapterMapper.to(chapter))
    }

    override suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        return chapterDAO.getChapterByBook(bibleId, bookId).map { chapterMapper.from(it) }
    }

    override suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return chapterMapper.from(chapterDAO.getChapter(chapterId, bibleId))
    }

}
