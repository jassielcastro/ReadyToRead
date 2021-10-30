package com.ajcm.domain.usecase.passage

import com.ajcm.domain.entity.Passage
import com.ajcm.domain.repository.IPassageRepository
import javax.inject.Inject

class GetPassageUC @Inject constructor(private val repository: IPassageRepository) {
    suspend operator fun invoke(id: String, passageId: String): Passage {
        return repository.getPassage(id, passageId)
    }
}
