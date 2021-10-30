package com.ajcm.data.repository

import com.ajcm.data.datasource.IRemoteAudioBibleDataSource
import com.ajcm.domain.repository.IAudioBibleRepository
import com.ajcm.domain.entity.AudioBible
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class AudioBibleRepository @Inject constructor(private val dataSource: IRemoteAudioBibleDataSource):
    IAudioBibleRepository {

    override suspend fun getAudioBibles(): List<BibleSummary> = dataSource.getAudioBibles()
    override suspend fun getAudioBible(bibleId: String): AudioBible = dataSource.getAudioBible(bibleId)

}
