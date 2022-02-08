package com.ajcm.bibles.datasource

import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.IRemoteAudioBibleDataSource
import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.Bible
import javax.inject.Inject

class RemoteAudioBibleDataSource @Inject constructor(private val service: BibleService) :
    IRemoteAudioBibleDataSource {

    override suspend fun getAudioBibles(): List<Bible> {
        return service.getAudioBiblesAsync().data
    }

    override suspend fun getAudioBible(bibleId: String): AudioBible {
        return service.getAudioBibleAsync(bibleId).data
    }
}
