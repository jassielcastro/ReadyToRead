package com.ajcm.domain.repository

import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.Bible

interface IAudioBibleRepository {
    suspend fun getAudioBibles(): List<Bible>
    suspend fun getAudioBible(bibleId: String): AudioBible
}