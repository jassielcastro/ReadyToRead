package com.ajcm.domain.usecase.search

import com.ajcm.domain.entity.Search
import com.ajcm.domain.repository.ISearchRepository
import javax.inject.Inject

class SearchUC @Inject constructor(private val repository: ISearchRepository) {
    suspend operator fun invoke(id: String, query: String): Search {
        return repository.search(id, query)
    }
}
