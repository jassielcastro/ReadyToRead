package com.ajcm.configuration.database

import androidx.room.*
import com.ajcm.configuration.database.model.ConfigurationDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigurationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfigurations(conf: ConfigurationDTO)

    @Transaction
    suspend fun updateConfiguration(conf: ConfigurationDTO) {
        deleteConfigurations()
        insertConfigurations(conf)
    }

    @Query("DELETE FROM ${ConfigurationDTO.TABLE_NAME}")
    suspend fun deleteConfigurations()

    @Query("SELECT * FROM ${ConfigurationDTO.TABLE_NAME} LIMIT 1")
    fun getConfiguration(): Flow<ConfigurationDTO>

}
