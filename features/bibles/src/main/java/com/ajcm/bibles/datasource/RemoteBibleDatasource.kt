package com.ajcm.bibles.datasource

import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.entity.Bible
import javax.inject.Inject

class RemoteBibleDatasource @Inject constructor(
    private val service: BibleService
) : IRemoteBibleDataSource {
    override suspend fun getBibles(): List<Bible> = service.getBibles().data

    override suspend fun getBible(bibleId: String): Bible =
        service.getBible(bibleId).data
}
