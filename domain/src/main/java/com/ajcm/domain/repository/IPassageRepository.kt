package com.ajcm.domain.repository

import com.ajcm.domain.entity.Passage

interface IPassageRepository {
    suspend fun getPassage(bibleId: String, passageId: String): Passage
}
