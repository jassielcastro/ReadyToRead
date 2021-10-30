package com.ajcm.domain.repository

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.BibleSummary

interface IBibleRepository {
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
