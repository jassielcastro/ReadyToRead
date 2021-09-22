package com.ajcm.book.di

import com.ajcm.annotation.AudioBookDataSource
import com.ajcm.annotation.AudioBookRepo
import com.ajcm.annotation.BookDataSource
import com.ajcm.annotation.BookRepo
import com.ajcm.book.datasource.RemoteAudioBookDataSource
import com.ajcm.book.datasource.RemoteBookDataSource
import com.ajcm.book.repository.AudioBookRepository
import com.ajcm.book.repository.BookRepository
import com.ajcm.book.service.BookService
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.data.repository.IBookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkBookModule {

    @Provides
    fun provideApi(retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }

}

@Module
@InstallIn(FragmentComponent::class)
abstract class BookModule {

    @BookDataSource
    @Binds
    abstract fun bindRemoteBookDataSource(datasource: RemoteBookDataSource): IRemoteBookDataSource

    @AudioBookDataSource
    @Binds
    abstract fun bindRemoteAudioBookDataSource(datasource: RemoteAudioBookDataSource): IRemoteBookDataSource

    @BookRepo
    @Binds
    abstract fun bindBookRepository(repository: BookRepository): IBookRepository

    @AudioBookRepo
    @Binds
    abstract fun bindAudioBookRepository(repository: AudioBookRepository): IBookRepository

}
