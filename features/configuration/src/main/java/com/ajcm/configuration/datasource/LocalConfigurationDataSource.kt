package com.ajcm.configuration.datasource

import com.ajcm.configuration.database.ConfigurationDAO
import com.ajcm.configuration.database.model.ConfigurationDTO
import com.ajcm.data.datasource.ILocalConfigurationDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Configuration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

class LocalConfigurationDataSource @Inject constructor(
    private val configurationDAO: ConfigurationDAO,
    private val configurationMapper: BaseMapper<Configuration, ConfigurationDTO>
) : ILocalConfigurationDataSource {
    override suspend fun getConfigurations(): Flow<Configuration> {
        return flow {
            configurationDAO.getConfiguration()
                .collect {
                    runCatching {
                        emit(configurationMapper.from(it))
                    }.onFailure {
                        configurationDAO.insertConfigurations(ConfigurationDTO())
                    }
                }
        }
    }

    override suspend fun updateConfigurations(config: Configuration) {
        configurationDAO.updateConfiguration(configurationMapper.to(config))
    }
}
