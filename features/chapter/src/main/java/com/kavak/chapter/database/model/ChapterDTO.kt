package com.kavak.chapter.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcm.domain.entity.NextChapter
import com.ajcm.domain.entity.PreviousChapter

@Entity(tableName = ChapterDTO.TABLE_NAME)
data class ChapterDTO(
    @PrimaryKey
    val id: String,
    val bibleId: String,
    val number: String,
    val bookId: String,
    val content: String?,
    val reference: String,
    val verseCount: Int?,
    var next: NextChapter,
    var previous: PreviousChapter,
    var copyright: String?
) {
    companion object {
        const val TABLE_NAME = "Chapter"
    }
}
