package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.repository.IAudioBibleRepository
import javax.inject.Inject

class GetAudioBibleUC @Inject constructor(private val repository: IAudioBibleRepository) {
    suspend operator fun invoke(id: String): AudioBible {
        return repository.getAudioBible(id)
    }
}
