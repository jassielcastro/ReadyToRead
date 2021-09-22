package com.ajcm.usecase.bible

import com.ajcm.data.repository.IAudioBibleRepository
import com.ajcm.data.usecase.BaseListUseCase
import com.ajcm.domain.BibleSummary
import javax.inject.Inject

class GetAudioBiblesUC @Inject constructor(private val repository: IAudioBibleRepository) :
    BaseListUseCase<BibleSummary> {
    override suspend fun invoke(): List<BibleSummary> = repository.getAudioBibles()
}
