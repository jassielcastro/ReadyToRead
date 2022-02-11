package com.ajcm.book.di

import com.ajcm.book.database.model.BookDTO
import com.ajcm.book.datasource.LocalBookDataSource
import com.ajcm.book.datasource.RemoteBookDataSource
import com.ajcm.book.mappers.BookMapper
import com.ajcm.book.service.BookService
import com.ajcm.data.datasource.ILocalBookDataSource
import com.ajcm.data.datasource.IRemoteBookDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.BookRepository
import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
@InstallIn(SingletonComponent::class)
abstract class BookModule {

    @Binds
    abstract fun bindRemoteBookDataSource(datasource: RemoteBookDataSource): IRemoteBookDataSource

    @Binds
    abstract fun bindLocalBookDataSource(datasource: LocalBookDataSource): ILocalBookDataSource

    @Binds
    abstract fun bindBookRepository(repository: BookRepository): IBookRepository

    @Binds
    abstract fun bindBookMapper(mapper: BookMapper): BaseMapper<Book, BookDTO>

}
