package com.ajcm.bibles.di

import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.bibles.datasource.LocalBibleDataSource
import com.ajcm.bibles.datasource.RemoteBibleDatasource
import com.ajcm.bibles.mappers.BiblesMapper
import com.ajcm.bibles.service.BibleService
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.BibleRepository
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IBibleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkBibleModule {

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): BibleService {
        return retrofit.create(BibleService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object BibleModule {

    @Provides
    fun bindRemoteBibleDataSource(
        dataSource: RemoteBibleDatasource
    ): IRemoteBibleDataSource = dataSource

    @Provides
    fun bindBibleRepository(
        repository: BibleRepository
    ): IBibleRepository = repository

    @Provides
    fun bindLocalBibleDataSource(
        dataSource: LocalBibleDataSource
    ): ILocalBibleDataSource = dataSource

    @Provides
    fun bindBiblesMapper(
        mapper: BiblesMapper
    ): BaseMapper<Bible, BibleDTO> = mapper

}
