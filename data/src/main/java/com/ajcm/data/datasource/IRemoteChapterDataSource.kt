package com.ajcm.data.datasource

import com.ajcm.domain.entity.Chapter

interface IRemoteChapterDataSource {
    suspend fun getChapters(bibleId: String, bookId: String): List<Chapter>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
}
