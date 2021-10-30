package com.ajcm.domain.usecase.bible

import com.ajcm.domain.repository.IAudioBibleRepository
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class GetAudioBiblesUC @Inject constructor(private val repository: IAudioBibleRepository) {
    suspend operator fun invoke(): List<BibleSummary> = repository.getAudioBibles()
}
