package com.ajcm.data.datasource

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.BibleSummary

interface IRemoteBibleDataSource {
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
