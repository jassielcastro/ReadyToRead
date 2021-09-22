package com.ajcm.bibles.di

import com.ajcm.bibles.datasource.RemoteAudioBibleDataSource
import com.ajcm.bibles.datasource.RemoteBibleDatasource
import com.ajcm.bibles.repository.AudioBibleRepository
import com.ajcm.bibles.repository.BibleRepository
import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.IRemoteAudioBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.data.repository.IAudioBibleRepository
import com.ajcm.data.repository.IBibleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkBibleModule {

    @Provides
    fun provideApi(retrofit: Retrofit): BibleService {
        return retrofit.create(BibleService::class.java)
    }

}

@Module
@InstallIn(FragmentComponent::class)
abstract class BibleModule {

    @Binds
    abstract fun bindRemoteBibleDataSource(remoteBibleDatasource: RemoteBibleDatasource): IRemoteBibleDataSource

    @Binds
    abstract fun bindBibleRepository(repository: BibleRepository): IBibleRepository

    @Binds
    abstract fun bindAudioBibleDataSource(dataSource: RemoteAudioBibleDataSource): IRemoteAudioBibleDataSource

    @Binds
    abstract fun bindAudioBibleRepository(repository: AudioBibleRepository): IAudioBibleRepository

}
