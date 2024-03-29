package com.ajcm.bible.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptorLogin

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptorHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiToken
