package com.ajcm.verse.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ajcm.verse.database.model.VerseDTO

@Dao
interface VerseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerses(verses: List<VerseDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerse(verse: VerseDTO)

    @Transaction
    fun insertOrUpdateVerse(verse: VerseDTO) {
        runCatching {
            getVerse(verse.id, verse.bibleId)
        }.onSuccess {
            updateVerse(verse)
        }.onFailure {
            insertVerse(verse)
        }
    }

    @Update
    fun updateVerse(verse: VerseDTO)

    @Query("SELECT * FROM ${VerseDTO.TABLE_NAME} WHERE bibleId = :bibleId AND chapterId = :chapterId")
    fun getVersesByChapter(bibleId: String, chapterId: String): List<VerseDTO>

    @Query("SELECT * FROM ${VerseDTO.TABLE_NAME} WHERE id = :verseId AND bibleId = :bibleId")
    fun getVerse(verseId: String, bibleId: String): VerseDTO

}
