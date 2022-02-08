package com.ajcm.book.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BookDTO.TABLE_NAME)
data class BookDTO(
    @PrimaryKey
    val id: String,
    val bibleId: String,
    val abbreviation: String,
    val name: String,
    val nameLong: String
) {
    companion object {
        const val TABLE_NAME = "Book"
    }
}
