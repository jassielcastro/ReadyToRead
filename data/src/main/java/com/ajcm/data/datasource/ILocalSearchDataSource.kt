package com.ajcm.data.datasource

import com.ajcm.domain.entity.Search

interface ILocalSearchDataSource {
    suspend fun search(bibleId: String, query: String): Search
}
