package com.ajcm.data.datasource

import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary

interface ILocalBibleDataSource {
    suspend fun saveBibles(bibleList: List<BibleSummary>)
    suspend fun saveBible(bible: Bible)

    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
