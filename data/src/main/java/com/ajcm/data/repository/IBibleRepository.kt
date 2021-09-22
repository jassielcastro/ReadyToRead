package com.ajcm.data.repository

import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary

interface IBibleRepository {
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
