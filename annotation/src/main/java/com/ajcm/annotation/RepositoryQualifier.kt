package com.ajcm.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BookRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AudioBookRepo
