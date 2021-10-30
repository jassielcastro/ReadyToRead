package com.ajcm.data.datasource

import com.ajcm.domain.entity.Passage

interface IRemotePassageDataSource {
    suspend fun getPassage(bibleId: String, passageId: String): Passage
}
