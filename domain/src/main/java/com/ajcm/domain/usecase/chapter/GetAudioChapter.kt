package com.ajcm.domain.usecase.chapter

import com.ajcm.domain.entity.AudioChapter
import com.ajcm.domain.repository.IChapterRepository
import javax.inject.Inject

class GetAudioChapter @Inject constructor(private val repository: IChapterRepository) {
    suspend operator fun invoke(id: String, chapterId: String): AudioChapter {
        return repository.getAudioChapter(id, chapterId)
    }
}
