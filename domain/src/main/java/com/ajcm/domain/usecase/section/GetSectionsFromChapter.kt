package com.ajcm.domain.usecase.section

import com.ajcm.domain.entity.SectionSummary
import com.ajcm.domain.repository.ISectionRepository
import javax.inject.Inject

class GetSectionsFromChapter @Inject constructor(private val repository: ISectionRepository) {
    suspend operator fun invoke(id: String, chapterId: String): List<SectionSummary> {
        return repository.getSectionsFromChapter(id, chapterId)
    }
}
