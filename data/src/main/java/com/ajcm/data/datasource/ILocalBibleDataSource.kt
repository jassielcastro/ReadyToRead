package com.ajcm.data.datasource

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.BibleSummary

interface ILocalBibleDataSource {
    suspend fun saveBibles(bibleList: List<BibleSummary>)
    suspend fun saveBible(bible: Bible)

    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
}
