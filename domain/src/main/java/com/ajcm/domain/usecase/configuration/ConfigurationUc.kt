package com.ajcm.domain.usecase.configuration

import com.ajcm.domain.entity.Configuration
import com.ajcm.domain.repository.IConfigurationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigurationUc @Inject constructor(
    private val localRepository: IConfigurationRepository
) {

    suspend fun getConfigurations(): Flow<Configuration> {
        return localRepository.getConfigurations()
    }

    suspend fun updateConfigurations(config: Configuration) {
        localRepository.updateConfigurations(config)
    }

}
