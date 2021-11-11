package com.ajcm.bibles.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.bibles.database.model.BibleSummaryDTO

@Dao
interface BibleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBibles(bibles: List<BibleSummaryDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBible(bible: BibleDTO)

    @Query("SELECT * FROM BibleSummary")
    fun getAllBibles(): List<BibleSummaryDTO>

    @Query("SELECT * FROM Bible WHERE id = :bibleId")
    fun getBible(bibleId: String): BibleDTO

}
