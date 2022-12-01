package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalConfigurationDataSource
import com.ajcm.domain.entity.Configuration
import com.ajcm.domain.repository.IConfigurationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigurationRepository @Inject constructor(
    private val localConfigurationDataSource: ILocalConfigurationDataSource
) : IConfigurationRepository {
    override suspend fun getConfigurations(): Flow<Configuration> {
        return localConfigurationDataSource.getConfigurations()
    }

    override suspend fun updateConfigurations(config: Configuration) {
        localConfigurationDataSource.updateConfigurations(config)
    }
}
