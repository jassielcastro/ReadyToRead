package com.ajcm.data.datasource

import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary

interface IRemoteBibleDataSource {
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
