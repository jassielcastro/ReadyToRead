package com.ajcm.bibles.datasource

import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.IRemoteAudioBibleDataSource
import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class RemoteAudioBibleDataSource @Inject constructor(private val service: BibleService) :
    IRemoteAudioBibleDataSource {

    override suspend fun getAudioBibles(): List<BibleSummary> {
        return service.getAudioBiblesAsync().await().data
    }

    override suspend fun getAudioBible(bibleId: String): AudioBible {
        return service.getAudioBibleAsync(bibleId).await().data
    }
}