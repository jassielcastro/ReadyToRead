package com.ajcm.usecase.search

import com.ajcm.data.repository.ISearchRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Search
import javax.inject.Inject

class SearchUC @Inject constructor(private val repository: ISearchRepository) : BaseUseCaseWithParams<Search, String> {
    override suspend fun invoke(vararg params: String): Search {
        return repository.search(params[0], params[1])
    }
}
