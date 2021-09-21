package com.ajcm.data.repository

import com.ajcm.domain.Passage

interface IPassageRepository {
    suspend fun getPassage(bibleId: String, passageId: String): Passage
}
