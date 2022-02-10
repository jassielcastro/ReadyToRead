package com.ajcm.data.datasource

import com.ajcm.domain.entity.Bible

interface ILocalBibleDataSource {
    suspend fun saveBibles(bibleList: List<Bible>)
    suspend fun saveBible(bible: Bible)

    suspend fun getBibles(): List<Bible>
    suspend fun getFavouriteBibles(): List<Bible>
    suspend fun getBible(bibleId: String): Bible
}
