package com.ajcm.bibles.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ajcm.bibles.database.model.BibleDTO

@Dao
interface BibleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBibles(bibles: List<BibleDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBible(bible: BibleDTO)

    @Transaction
    fun insertOrUpdateBible(bible: BibleDTO) {
        runCatching {
            getBible(bible.id)
        }.onSuccess {
            updateBible(bible)
        }.onFailure {
            insertBible(bible)
        }
    }

    @Update
    fun updateBible(bible: BibleDTO)

    @Query("SELECT * FROM ${BibleDTO.TABLE_NAME}")
    fun getAllBibles(): List<BibleDTO>

    @Query("SELECT * FROM ${BibleDTO.TABLE_NAME} WHERE isFavourite == 1")
    fun getFavouriteBibles(): List<BibleDTO>

    @Query("SELECT * FROM ${BibleDTO.TABLE_NAME} WHERE id = :bibleId")
    fun getBible(bibleId: String): BibleDTO

}
