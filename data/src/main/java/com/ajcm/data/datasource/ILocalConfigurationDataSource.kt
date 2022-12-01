package com.ajcm.data.datasource

import com.ajcm.domain.entity.Configuration
import kotlinx.coroutines.flow.Flow

interface ILocalConfigurationDataSource {
    suspend fun getConfigurations(): Flow<Configuration>
    suspend fun updateConfigurations(config: Configuration)
}
