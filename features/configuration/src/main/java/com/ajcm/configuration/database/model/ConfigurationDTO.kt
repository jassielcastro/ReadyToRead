package com.ajcm.configuration.database.model

import androidx.annotation.IntRange
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ConfigurationDTO.TABLE_NAME)
data class ConfigurationDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @IntRange(from = 1, to = 5)
    val textSizeMultiplier: Int = DEFAULT
) {
    companion object {
        const val TABLE_NAME = "Configuration"
        const val DEFAULT = 2
    }
}
