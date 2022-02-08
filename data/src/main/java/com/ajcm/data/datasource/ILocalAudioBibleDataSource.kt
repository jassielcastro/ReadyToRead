package com.ajcm.data.datasource

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.Bible

interface ILocalAudioBibleDataSource {
    suspend fun saveBibles(bibleList: List<Bible>)
    suspend fun saveAudioBible(audioBible: AudioBible)
    suspend fun getBibles(): List<Bible>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
