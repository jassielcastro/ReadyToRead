package com.ajcm.usecase.section

import com.ajcm.data.repository.ISectionRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Section
import javax.inject.Inject

class GetSectionUC @Inject constructor(private val repository: ISectionRepository) :
    BaseUseCaseWithParams<Section, String> {
    override suspend fun invoke(vararg params: String): Section {
        return repository.getSection(params[0], params[1])
    }
}
