package com.ajcm.usecase.verse

import com.ajcm.data.repository.IVerseRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Verse
import javax.inject.Inject

class GetVerseUC @Inject constructor(private val repository: IVerseRepository) : BaseUseCaseWithParams<Verse, String> {
    override suspend fun invoke(vararg params: String): Verse {
        return repository.getVerse(params[0], params[1])
    }
}
