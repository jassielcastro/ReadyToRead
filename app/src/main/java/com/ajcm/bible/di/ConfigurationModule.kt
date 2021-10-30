package com.ajcm.bible.di

import com.ajcm.bible.network.HeaderInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ConfigurationModule {

    @Named(BASE_URL)
    @Provides
    fun provideUrl(): String = "https://api.scripture.api.bible"

    @Named(API_TOKEN)
    @Provides
    fun provideApiToken(): String = "78f3d242b9698359bf237a6816232629"

    @Provides
    fun provideConnectionSpec(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .build()
    }

    @Named(LOGIN_INTERCEPTOR)
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Named(HEADER_INTERCEPTOR)
    @Provides
    fun provideHeaderInterceptor(@Named(API_TOKEN) apiToken: String): Interceptor {
        return HeaderInterceptor(apiToken)
    }

    @Provides
    fun provideOkHttpClient(
        cs: ConnectionSpec,
        @Named(LOGIN_INTERCEPTOR) loggingInterceptor: Interceptor,
        @Named(HEADER_INTERCEPTOR) headerInterceptor: Interceptor
    ): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
        okhttp.writeTimeout(30, TimeUnit.SECONDS)
        okhttp.readTimeout(30, TimeUnit.SECONDS)
        okhttp.connectionSpecs(listOf(cs))
        okhttp.addInterceptor(loggingInterceptor)
        okhttp.addInterceptor(headerInterceptor)
        return okhttp.build()
    }

    @Provides
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
