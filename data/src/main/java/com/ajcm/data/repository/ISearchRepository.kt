package com.ajcm.data.repository

import com.ajcm.domain.Search

interface ISearchRepository {
    suspend fun search(bibleId: String, query: String): Search
}