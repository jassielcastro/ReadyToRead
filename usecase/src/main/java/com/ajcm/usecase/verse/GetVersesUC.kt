package com.ajcm.usecase.verse

import com.ajcm.data.repository.IVerseRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.VerseSummary

class GetVersesUC(private val repository: IVerseRepository) :
    BaseListUseCaseWithParams<VerseSummary, String> {
    override suspend fun invoke(vararg params: String): List<VerseSummary> {
        return repository.getVerses(params[0], params[1])
    }
}
