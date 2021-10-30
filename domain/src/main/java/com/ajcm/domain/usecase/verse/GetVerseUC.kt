package com.ajcm.domain.usecase.verse

import com.ajcm.domain.entity.Verse
import com.ajcm.domain.repository.IVerseRepository
import javax.inject.Inject

class GetVerseUC @Inject constructor(private val repository: IVerseRepository) {
    suspend operator fun invoke(id: String, verseId: String): Verse {
        return repository.getVerse(id, verseId)
    }
}
