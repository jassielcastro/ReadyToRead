package com.ajcm.bibles.repository

import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.data.repository.IBibleRepository
import com.ajcm.domain.AudioBible
import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary
import javax.inject.Inject

class BibleRepository @Inject constructor(private val dataSource: IRemoteBibleDataSource): IBibleRepository {
    override suspend fun getBibles(): List<BibleSummary> = dataSource.getBibles()

    override suspend fun getBible(bibleId: String): Bible = dataSource.getBible(bibleId)
}
