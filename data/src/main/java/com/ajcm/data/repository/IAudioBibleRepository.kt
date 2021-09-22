package com.ajcm.data.repository

import com.ajcm.domain.AudioBible
import com.ajcm.domain.BibleSummary

interface IAudioBibleRepository {
    suspend fun getAudioBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}