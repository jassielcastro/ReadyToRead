package com.ajcm.bibles.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcm.bibles.database.model.BibleDTO

@Dao
interface BibleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBibles(bibles: List<BibleDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBible(bible: BibleDTO)

    @Query("SELECT * FROM ${BibleDTO.TABLE_NAME}")
    fun getAllBibles(): List<BibleDTO>

    @Query("SELECT * FROM ${BibleDTO.TABLE_NAME} WHERE id = :bibleId")
    fun getBible(bibleId: String): BibleDTO

}
