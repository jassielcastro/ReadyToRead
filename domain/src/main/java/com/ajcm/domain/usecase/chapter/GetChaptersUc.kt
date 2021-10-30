package com.ajcm.domain.usecase.chapter

import com.ajcm.domain.entity.ChapterSummary
import com.ajcm.domain.repository.IChapterRepository
import javax.inject.Inject

class GetChaptersUc @Inject constructor(private val repository: IChapterRepository) {
    suspend operator fun invoke(id: String, bookId: String): List<ChapterSummary> {
        return repository.getChapters(id, bookId)
    }
}
