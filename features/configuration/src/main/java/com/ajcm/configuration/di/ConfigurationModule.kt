package com.ajcm.configuration.di

import com.ajcm.configuration.database.model.ConfigurationDTO
import com.ajcm.configuration.datasource.LocalConfigurationDataSource
import com.ajcm.configuration.mappers.ConfigurationMapper
import com.ajcm.data.datasource.ILocalConfigurationDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.data.repository.ConfigurationRepository
import com.ajcm.domain.entity.Configuration
import com.ajcm.domain.repository.IConfigurationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigurationModule {
    @Binds
    abstract fun bindLocalConfigurationDataSource(datasource: LocalConfigurationDataSource): ILocalConfigurationDataSource

    @Binds
    abstract fun bindConfigurationMapper(mapper: ConfigurationMapper): BaseMapper<Configuration, ConfigurationDTO>

    @Binds
    abstract fun bindConfigurationRepository(repository: ConfigurationRepository): IConfigurationRepository
}
