package com.ajcm.chapter.di

import com.ajcm.chapter.database.model.ChapterDTO
import com.ajcm.chapter.datasource.LocalChapterDataSource
import com.ajcm.chapter.datasource.RemoteChapterDataSource
import com.ajcm.chapter.mappers.ChapterMapper
import com.ajcm.chapter.service.ChapterService
import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.datasource.IRemoteChapterDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.ChapterRepository
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkChapterModule {

    @Provides
    fun provideApi(retrofit: Retrofit): ChapterService {
        return retrofit.create(ChapterService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class ChapterModule {

    @Binds
    abstract fun bindRemoteChapterDataSource(datasource: RemoteChapterDataSource): IRemoteChapterDataSource

    @Binds
    abstract fun bindLocalChapterDataSource(datasource: LocalChapterDataSource): ILocalChapterDataSource

    @Binds
    abstract fun bindChapterRepository(repository: ChapterRepository): IChapterRepository

    @Binds
    abstract fun bindChapterMapper(mapper: ChapterMapper): BaseMapper<Chapter, ChapterDTO>

}
