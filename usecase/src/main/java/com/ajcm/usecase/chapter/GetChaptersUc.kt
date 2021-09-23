package com.ajcm.usecase.chapter

import com.ajcm.data.repository.IChapterRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.ChapterSummary
import javax.inject.Inject

class GetChaptersUc @Inject constructor(private val repository: IChapterRepository) : BaseListUseCaseWithParams<ChapterSummary, String> {
    override suspend fun invoke(id: String, vararg params: String): List<ChapterSummary> {
        return repository.getChapters(id, params[0])
    }
}
