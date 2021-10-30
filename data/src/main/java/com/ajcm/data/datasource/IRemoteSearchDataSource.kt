package com.ajcm.data.datasource

import com.ajcm.domain.entity.Search

interface IRemoteSearchDataSource {
    suspend fun search(bibleId: String, query: String): Search
}
