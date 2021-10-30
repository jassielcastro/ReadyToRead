package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.BibleSummary
import com.ajcm.domain.repository.IBibleRepository
import javax.inject.Inject

class GetBiblesUc @Inject constructor(private val repository: IBibleRepository) {
    suspend operator fun invoke(): List<BibleSummary> = repository.getBibles()
}
