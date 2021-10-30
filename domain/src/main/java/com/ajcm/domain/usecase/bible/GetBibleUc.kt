package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IBibleRepository
import javax.inject.Inject

class GetBibleUc @Inject constructor(private val repository: IBibleRepository) {
    suspend operator fun invoke(id: String): Bible {
        return repository.getBible(id)
    }
}
