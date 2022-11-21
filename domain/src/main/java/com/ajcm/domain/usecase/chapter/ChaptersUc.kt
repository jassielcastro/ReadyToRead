package com.ajcm.domain.usecase.chapter

import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import javax.inject.Inject

class ChaptersUc @Inject constructor(
    private val repository: IChapterRepository
) {

    suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        return repository.getChapters(bibleId, bookId)
    }

    suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return repository.getChapter(bibleId, chapterId)
    }
}
