package com.ajcm.data.datasource

import com.ajcm.domain.entity.Bible

interface IRemoteBibleDataSource {
    suspend fun getBibles(): List<Bible>
    suspend fun getBible(bibleId: String): Bible
}
