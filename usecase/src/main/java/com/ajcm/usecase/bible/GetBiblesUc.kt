package com.ajcm.usecase.bible

import com.ajcm.data.repository.IBibleRepository
import com.ajcm.data.usecase.BaseListUseCase
import com.ajcm.domain.BibleSummary
import javax.inject.Inject

class GetBiblesUc @Inject constructor(private val repository: IBibleRepository) : BaseListUseCase<BibleSummary> {
    override suspend fun invoke(): List<BibleSummary> = repository.getBibles()
}
