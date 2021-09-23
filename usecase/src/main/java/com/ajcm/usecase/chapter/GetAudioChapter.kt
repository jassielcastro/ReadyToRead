package com.ajcm.usecase.chapter

import com.ajcm.data.repository.IChapterRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.AudioChapter
import javax.inject.Inject

class GetAudioChapter @Inject constructor(private val repository: IChapterRepository) :
    BaseUseCaseWithParams<AudioChapter, String> {
    override suspend fun invoke(id: String, vararg params: String): AudioChapter {
        return repository.getAudioChapter(id, params[0])
    }
}
