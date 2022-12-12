package com.ajcm.bible.di

import com.ajcm.bible.BuildConfig
import com.ajcm.bible.network.HeaderInterceptor
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

@Module
@InstallIn(SingletonComponent::class)
class ConfigurationModule {

    @BaseUrl
    @Provides
    fun provideUrl(): String = "https://api.scripture.api.bible"

    @ApiToken
    @Provides
    fun provideApiToken(): String = BuildConfig.API_TOKEN

    @Provides
    fun provideConnectionSpec(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .build()
    }

    @InterceptorLogin
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @InterceptorHeader
    @Provides
    fun provideHeaderInterceptor(@ApiToken apiToken: String): Interceptor {
        return HeaderInterceptor(apiToken)
    }

    @Provides
    fun provideOkHttpClient(
        cs: ConnectionSpec,
        @InterceptorLogin loggingInterceptor: Interceptor,
        @InterceptorHeader headerInterceptor: Interceptor
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
    fun provideRetrofit(@BaseUrl baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
