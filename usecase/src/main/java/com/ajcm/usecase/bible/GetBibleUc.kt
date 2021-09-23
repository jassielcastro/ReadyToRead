package com.ajcm.usecase.bible

import com.ajcm.data.repository.IBibleRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Bible
import javax.inject.Inject

class GetBibleUc @Inject constructor(private val repository: IBibleRepository) : BaseUseCaseWithParams<Bible, String> {
    override suspend fun invoke(id: String, vararg params: String): Bible {
        return repository.getBible(id)
    }
}
