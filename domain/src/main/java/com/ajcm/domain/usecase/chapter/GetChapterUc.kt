package com.ajcm.domain.usecase.chapter

import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import javax.inject.Inject

class GetChapterUc @Inject constructor(private val repository: IChapterRepository) {
    suspend operator fun invoke(id: String, chapterId: String): Chapter {
        return repository.getChapter(id, chapterId)
    }
}
