package com.ajcm.usecase.section

import com.ajcm.data.repository.ISectionRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Section
import javax.inject.Inject

class GetSectionUC @Inject constructor(private val repository: ISectionRepository) :
    BaseUseCaseWithParams<Section, String> {
    override suspend fun invoke(id: String, vararg params: String): Section {
        return repository.getSection(id, params[0])
    }
}
