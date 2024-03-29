package com.ajcm.data.datasource

import com.ajcm.domain.entity.Bible
import kotlinx.coroutines.flow.Flow

interface ILocalBibleDataSource {
    suspend fun saveBibles(bibleList: List<Bible>)
    suspend fun saveBible(bible: Bible)

    suspend fun getBibles(): List<Bible>
    suspend fun getFavouriteBibles(): Flow<List<Bible>>
    suspend fun getBible(bibleId: String): Bible

    suspend fun toggleFavorite(bibleId: String): Bible
}
