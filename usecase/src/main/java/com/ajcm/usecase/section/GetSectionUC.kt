package com.ajcm.usecase.section

import com.ajcm.data.repository.ISectionRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Section

class GetSectionUC(private val repository: ISectionRepository) :
    BaseUseCaseWithParams<Section, String> {
    override suspend fun invoke(vararg params: String): Section {
        return repository.getSection(params[0], params[1])
    }
}
