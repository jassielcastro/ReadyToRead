package com.ajcm.domain.repository

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.BibleSummary

interface IAudioBibleRepository {
    suspend fun getAudioBibles(): List<BibleSummary>
    suspend fun getAudioBible(bibleId: String): AudioBible
}