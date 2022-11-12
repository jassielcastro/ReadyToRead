package com.ajcm.domain.repository

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import kotlinx.coroutines.flow.Flow

interface IBibleRepository {
    suspend fun getBibles(request: GetBibleRequest): List<Bible>
    suspend fun getFavouriteBibles(): Flow<List<Bible>>
    suspend fun getBible(bibleId: String): Bible
    suspend fun toggleFavorite(bibleId: String): Bible
}
