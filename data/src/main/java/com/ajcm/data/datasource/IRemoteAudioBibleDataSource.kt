package com.ajcm.data.datasource

import com.ajcm.domain.AudioBible
import com.ajcm.domain.BibleSummary

interface IRemoteAudioBibleDataSource {
    suspend fun getAudioBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
