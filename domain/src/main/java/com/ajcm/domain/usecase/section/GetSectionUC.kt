package com.ajcm.domain.usecase.section

import com.ajcm.domain.entity.Section
import com.ajcm.domain.repository.ISectionRepository
import javax.inject.Inject

class GetSectionUC @Inject constructor(private val repository: ISectionRepository) {
    suspend operator fun invoke(id: String, sectionId: String): Section {
        return repository.getSection(id, sectionId)
    }
}
