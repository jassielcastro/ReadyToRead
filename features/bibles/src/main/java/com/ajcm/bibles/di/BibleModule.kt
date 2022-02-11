package com.ajcm.bibles.di

import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.bibles.datasource.LocalBibleDataSource
import com.ajcm.bibles.datasource.RemoteBibleDatasource
import com.ajcm.bibles.mappers.BiblesMapper
import com.ajcm.bibles.service.BibleService
import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
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
@InstallIn(SingletonComponent::class)
class BibleModule {

    @Provides
    fun bindRemoteBibleDataSource(
        service: BibleService
    ): IRemoteBibleDataSource {
        return RemoteBibleDatasource(service)
    }

    @Provides
    fun bindBibleRepository(
        localDataSource: ILocalBibleDataSource,
        remoteDataSource: IRemoteBibleDataSource
    ): IBibleRepository {
        return BibleRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun bindLocalBibleDataSource(
        bibleDAO: BibleDAO,
        mapper: BaseMapper<Bible, BibleDTO>,
        @MainScope
        mainScope: CoroutineScope,
        @IoDispatcher
        ioDispatcher: CoroutineDispatcher
    ): ILocalBibleDataSource {
        return LocalBibleDataSource(
            bibleDAO,
            mapper,
            mainScope,
            ioDispatcher
        )
    }

    @Provides
    fun bindBiblesMapper(): BaseMapper<Bible, BibleDTO> {
        return BiblesMapper()
    }

}
