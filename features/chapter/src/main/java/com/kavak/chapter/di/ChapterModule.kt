package com.kavak.chapter.di

import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.datasource.IRemoteChapterDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.ChapterRepository
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import com.kavak.chapter.database.model.ChapterDTO
import com.kavak.chapter.datasource.LocalChapterDataSource
import com.kavak.chapter.datasource.RemoteChapterDataSource
import com.kavak.chapter.mappers.ChapterMapper
import com.kavak.chapter.service.ChapterService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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
@InstallIn(ActivityComponent::class)
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
