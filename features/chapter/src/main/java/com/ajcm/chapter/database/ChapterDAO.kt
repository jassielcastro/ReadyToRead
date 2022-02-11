package com.ajcm.chapter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ajcm.chapter.database.model.ChapterDTO

@Dao
interface ChapterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapters(chapters: List<ChapterDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapter(chapter: ChapterDTO)

    @Transaction
    fun insertOrUpdateChapter(chapter: ChapterDTO) {
        runCatching {
            getChapter(chapter.id, chapter.bibleId)
        }.onSuccess {
            updateChapter(chapter)
        }.onFailure {
            insertChapter(chapter)
        }
    }

    @Update
    fun updateChapter(book: ChapterDTO)

    @Query("SELECT * FROM ${ChapterDTO.TABLE_NAME} WHERE bibleId = :bibleId AND bookId = :bookId")
    fun getChapterByBook(bibleId: String, bookId: String): List<ChapterDTO>

    @Query("SELECT * FROM ${ChapterDTO.TABLE_NAME} WHERE id = :chapterId AND bibleId = :bibleId")
    fun getChapter(chapterId: String, bibleId: String): ChapterDTO

}
