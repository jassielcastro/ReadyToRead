package com.ajcm.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BookDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AudioBookDataSource
