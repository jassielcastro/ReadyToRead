package com.ajcm.bible.di

import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @MainScope
    @Provides
    fun provideMainScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    @IoDispatcher
    @Provides
    fun provideIoScope(): CoroutineDispatcher = Dispatchers.IO

}
