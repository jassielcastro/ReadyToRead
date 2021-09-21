package com.ajcm.usecase.section

import com.ajcm.data.repository.ISectionRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.SectionSummary
import javax.inject.Inject

class GetSectionsFromBookUC @Inject constructor(private val repository: ISectionRepository) :
    BaseListUseCaseWithParams<SectionSummary, String> {
    override suspend fun invoke(vararg params: String): List<SectionSummary> {
        return repository.getSectionsFromBook(params[0], params[1])
    }
}
