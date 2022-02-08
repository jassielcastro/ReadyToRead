package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IAudioBibleRepository
import javax.inject.Inject

class GetAudioBiblesUC @Inject constructor(private val repository: IAudioBibleRepository) {
    suspend operator fun invoke(): List<Bible> = repository.getAudioBibles()
}
