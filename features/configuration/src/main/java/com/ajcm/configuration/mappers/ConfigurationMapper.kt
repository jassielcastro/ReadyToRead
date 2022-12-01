package com.ajcm.configuration.mappers

import com.ajcm.configuration.database.model.ConfigurationDTO
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Configuration
import javax.inject.Inject

class ConfigurationMapper @Inject constructor() : BaseMapper<Configuration, ConfigurationDTO> {
    override fun from(e: ConfigurationDTO): Configuration {
        return Configuration(
            e.id,
            e.textSizeMultiplier
        )
    }

    override fun to(t: Configuration): ConfigurationDTO {
        return ConfigurationDTO(
            t.id,
            t.textSizeMultiplier
        )
    }
}
