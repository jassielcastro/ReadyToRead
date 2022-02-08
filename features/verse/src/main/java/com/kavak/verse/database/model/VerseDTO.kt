package com.kavak.verse.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcm.domain.entity.NextChapter
import com.ajcm.domain.entity.PreviousChapter

@Entity(tableName = VerseDTO.TABLE_NAME)
data class VerseDTO(
    @PrimaryKey
    val id: String,
    val orgId: String,
    val bibleId: String,
    val bookId: String,
    val chapterId: String,
    val content: String?,
    val reference: String?,
    val verseCount: Int?,
    val copyright: String?,
    val next: NextChapter?,
    val previous: PreviousChapter?
) {
    companion object {
        const val TABLE_NAME = "Verse"
    }
}
