package com.ajcm.domain.usecase.section

import com.ajcm.domain.entity.Section
import com.ajcm.domain.repository.ISectionRepository
import javax.inject.Inject

class GetSectionsFromBookUC @Inject constructor(private val repository: ISectionRepository) {
    suspend operator fun invoke(id: String, bookId: String): List<Section> {
        return repository.getSectionsFromBook(id, bookId)
    }
}
