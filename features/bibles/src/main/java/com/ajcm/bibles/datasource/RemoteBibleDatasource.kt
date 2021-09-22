package com.ajcm.bibles.datasource

import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.AudioBible
import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary
import javax.inject.Inject

class RemoteBibleDatasource @Inject constructor(private val service: BibleService) : IRemoteBibleDataSource {
    override suspend fun getBibles(): List<BibleSummary> = service.getBiblesAsync().await().data

    override suspend fun getBible(bibleId: String): Bible = service.getBibleAsync(bibleId).await().data
}
