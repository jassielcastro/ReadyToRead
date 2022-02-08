package com.ajcm.data.datasource

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.Bible

interface IRemoteAudioBibleDataSource {
    suspend fun getAudioBibles(): List<Bible>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
