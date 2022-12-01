package com.ajcm.domain.repository

import com.ajcm.domain.entity.Configuration
import kotlinx.coroutines.flow.Flow

interface IConfigurationRepository {
    suspend fun getConfigurations(): Flow<Configuration>
    suspend fun updateConfigurations(config: Configuration)
}
