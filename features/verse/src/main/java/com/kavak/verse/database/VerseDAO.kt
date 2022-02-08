package com.kavak.verse.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kavak.verse.database.model.VerseDTO

@Dao
interface VerseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerses(verses: List<VerseDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerse(verse: VerseDTO)

    @Query("SELECT * FROM ${VerseDTO.TABLE_NAME} WHERE bibleId = :bibleId AND chapterId = :chapterId")
    fun getVersesByChapter(bibleId: String, chapterId: String): List<VerseDTO>

    @Query("SELECT * FROM ${VerseDTO.TABLE_NAME} WHERE id = :verseId AND bibleId = :bibleId")
    fun getVerse(verseId: String, bibleId: String): VerseDTO

}
