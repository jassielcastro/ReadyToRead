package com.ajcm.domain.usecase.verse

import com.ajcm.domain.entity.Verse
import com.ajcm.domain.repository.IVerseRepository
import javax.inject.Inject

class GetVersesUC @Inject constructor(private val repository: IVerseRepository) {
    suspend operator fun invoke(bibleId: String, chapterId: String): List<Verse> {
        return repository.getVerses(bibleId, chapterId)
    }
}
