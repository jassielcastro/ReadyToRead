package com.ajcm.domain.repository

import com.ajcm.domain.entity.Chapter

interface IChapterRepository {
    suspend fun getChapters(bibleId: String, bookId: String): List<Chapter>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
}
