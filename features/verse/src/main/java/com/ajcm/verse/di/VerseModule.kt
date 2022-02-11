package com.ajcm.verse.di

import com.ajcm.data.datasource.ILocalVerseDataSource
import com.ajcm.data.datasource.IRemoteVerseDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.VerseRepository
import com.ajcm.domain.entity.Verse
import com.ajcm.domain.repository.IVerseRepository
import com.ajcm.verse.database.model.VerseDTO
import com.ajcm.verse.datasource.LocalVerseDataSource
import com.ajcm.verse.datasource.RemoteVerseDataSource
import com.ajcm.verse.mappers.VerseMapper
import com.ajcm.verse.service.VerseService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkVerseModule {

    @Provides
    fun provideApi(retrofit: Retrofit): VerseService {
        return retrofit.create(VerseService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class VerseModule {

    @Binds
    abstract fun bindRemoteVerseDataSource(datasource: RemoteVerseDataSource): IRemoteVerseDataSource

    @Binds
    abstract fun bindLocalVerseDataSource(datasource: LocalVerseDataSource): ILocalVerseDataSource

    @Binds
    abstract fun bindVerseRepository(repository: VerseRepository): IVerseRepository

    @Binds
    abstract fun bindVerseMapper(mapper: VerseMapper): BaseMapper<Verse, VerseDTO>

}
