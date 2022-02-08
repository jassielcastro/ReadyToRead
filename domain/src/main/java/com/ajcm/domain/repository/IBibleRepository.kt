package com.ajcm.domain.repository

import com.ajcm.domain.entity.Bible

interface IBibleRepository {
    suspend fun getBibles(): List<Bible>
    suspend fun getBible(bibleId: String): Bible
}
