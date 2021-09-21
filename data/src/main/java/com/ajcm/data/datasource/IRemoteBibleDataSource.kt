package com.ajcm.data.datasource

import com.ajcm.domain.AudioBible
import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary

interface IRemoteBibleDataSource {
    suspend fun getBibles(): List<BibleSummary>
    suspend fun getBible(bibleId: String): Bible
    suspend fun getAudioBible(bibleId: String): AudioBible
}
