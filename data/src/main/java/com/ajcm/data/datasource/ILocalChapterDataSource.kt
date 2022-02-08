package com.ajcm.data.datasource

import com.ajcm.domain.entity.Chapter

interface ILocalChapterDataSource {
    suspend fun saveChapters(chapters: List<Chapter>)
    suspend fun saveChapter(chapter: Chapter)

    suspend fun getChapters(bibleId: String, bookId: String): List<Chapter>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
}
