package com.ajcm.usecase.chapter

import com.ajcm.data.repository.IChapterRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Chapter
import javax.inject.Inject

class GetChapterUc @Inject constructor(private val repository: IChapterRepository) :
    BaseUseCaseWithParams<Chapter, String> {
    override suspend fun invoke(id: String, vararg params: String): Chapter {
        return repository.getChapter(id, params[0])
    }
}
