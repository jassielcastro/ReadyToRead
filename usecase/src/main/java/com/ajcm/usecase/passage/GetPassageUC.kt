package com.ajcm.usecase.passage

import com.ajcm.data.repository.IPassageRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Passage
import javax.inject.Inject

class GetPassageUC @Inject constructor(private val repository: IPassageRepository) :
    BaseUseCaseWithParams<Passage, String> {
    override suspend fun invoke(vararg params: String): Passage {
        return repository.getPassage(params[0], params[1])
    }
}
