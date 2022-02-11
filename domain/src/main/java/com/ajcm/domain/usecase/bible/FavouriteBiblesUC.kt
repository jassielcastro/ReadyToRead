package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IBibleRepository
import javax.inject.Inject

class FavouriteBiblesUC @Inject constructor(
    private val repository: IBibleRepository
) {
    suspend operator fun invoke(): List<Bible> = repository.getFavouriteBibles()
}
