package com.ajcm.bibles.di

import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.bibles.datasource.LocalBibleDataSource
import com.ajcm.bibles.datasource.RemoteAudioBibleDataSource
import com.ajcm.bibles.datasource.RemoteBibleDatasource
import com.ajcm.bibles.mappers.BiblesMapper
import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteAudioBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.AudioBibleRepository
import com.ajcm.data.repository.BibleRepository
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IAudioBibleRepository
import com.ajcm.domain.repository.IBibleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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
@InstallIn(ActivityComponent::class)
abstract class BibleModule {

    @Binds
    abstract fun bindRemoteBibleDataSource(remoteBibleDatasource: RemoteBibleDatasource): IRemoteBibleDataSource

    @Binds
    abstract fun bindBibleRepository(repository: BibleRepository): IBibleRepository

    @Binds
    abstract fun bindAudioBibleDataSource(dataSource: RemoteAudioBibleDataSource): IRemoteAudioBibleDataSource

    @Binds
    abstract fun bindAudioBibleRepository(repository: AudioBibleRepository): IAudioBibleRepository

    @Binds
    abstract fun bindLocalBibleDataSource(dataSource: LocalBibleDataSource): ILocalBibleDataSource

    @Binds
    abstract fun bindBiblesMapper(mapper: BiblesMapper): BaseMapper<Bible, BibleDTO>

}
