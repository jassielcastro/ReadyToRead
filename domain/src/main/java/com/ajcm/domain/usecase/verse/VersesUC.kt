package com.ajcm.domain.usecase.verse

import com.ajcm.domain.entity.Verse
import com.ajcm.domain.repository.IVerseRepository
import javax.inject.Inject

class VersesUC @Inject constructor(private val repository: IVerseRepository) {

    suspend fun getVerses(bibleId: String, chapterId: String): List<Verse> {
        return repository.getVerses(bibleId, chapterId)
    }

    suspend fun getVerse(bibleId: String, verseId: String): Verse {
        return repository.getVerse(bibleId, verseId)
    }
}
