package com.ajcm.data.datasource

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.BibleSummary

interface ILocalAudioBibleDataSource {
    suspend fun saveBibles(bibleList: List<BibleSummary>)
    suspend fun saveAudioBible(audioBible: AudioBible)
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
