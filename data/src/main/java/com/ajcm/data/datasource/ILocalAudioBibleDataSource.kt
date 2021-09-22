package com.ajcm.data.datasource

import com.ajcm.domain.AudioBible
import com.ajcm.domain.BibleSummary

interface ILocalAudioBibleDataSource {
    suspend fun saveBibles(bibleList: List<BibleSummary>)
    suspend fun saveAudioBible(audioBible: AudioBible)
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
