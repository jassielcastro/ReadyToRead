package com.ajcm.data.repository

import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.repository.IBibleRepository
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class BibleRepository @Inject constructor(private val dataSource: IRemoteBibleDataSource): IBibleRepository {
    override suspend fun getBibles(): List<BibleSummary> = dataSource.getBibles()

    override suspend fun getBible(bibleId: String): Bible = dataSource.getBible(bibleId)
}
