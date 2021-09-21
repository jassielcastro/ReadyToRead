package com.ajcm.usecase.bible

import com.ajcm.data.repository.IBibleRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Bible

class GetBibleUc(private val repository: IBibleRepository) : BaseUseCaseWithParams<Bible, String> {
    override suspend fun invoke(vararg params: String): Bible {
        return repository.getBible(params[0])
    }
}
