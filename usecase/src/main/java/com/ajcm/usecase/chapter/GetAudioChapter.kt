package com.ajcm.usecase.chapter

import com.ajcm.data.repository.IChapterRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.AudioChapter

class GetAudioChapter(private val repository: IChapterRepository) :
    BaseUseCaseWithParams<AudioChapter, String> {
    override suspend fun invoke(vararg params: String): AudioChapter {
        return repository.getAudioChapter(params[0], params[1])
    }
}
