package com.ajcm.domain.repository

import com.ajcm.domain.entity.Search

interface ISearchRepository {
    suspend fun search(bibleId: String, query: String): Search
}