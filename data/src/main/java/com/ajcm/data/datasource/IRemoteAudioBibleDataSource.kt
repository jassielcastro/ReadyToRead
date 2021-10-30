package com.ajcm.data.datasource

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.BibleSummary

interface IRemoteAudioBibleDataSource {
    suspend fun getAudioBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}
