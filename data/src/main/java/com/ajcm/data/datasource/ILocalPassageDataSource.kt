package com.ajcm.data.datasource

import com.ajcm.domain.entity.Passage

interface ILocalPassageDataSource {
    suspend fun savePassage(passage: Passage)
    suspend fun getPassage(bibleId: String, passageId: String): Passage
}
