package com.ajcm.domain.repository

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest

interface IBibleRepository {
    suspend fun getBibles(request: GetBibleRequest): List<Bible>
    suspend fun getFavouriteBibles(): List<Bible>
    suspend fun getBible(bibleId: String): Bible
    suspend fun toggleFavorite(bibleId: String): Bible
}
