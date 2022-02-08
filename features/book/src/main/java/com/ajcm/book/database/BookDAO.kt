package com.ajcm.book.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ajcm.book.database.model.BookDTO

@Dao
interface BookDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(book: List<BookDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookDTO)

    @Transaction
    fun insertOrUpdateBook(book: BookDTO) {
        runCatching {
            getBook(book.id, book.bibleId)
        }.onSuccess {
            updateBook(book)
        }.onFailure {
            insertBook(book)
        }
    }

    @Update
    fun updateBook(book: BookDTO)

    @Query("SELECT * FROM ${BookDTO.TABLE_NAME} WHERE bibleId = :bibleId")
    fun getAllBooksByBible(bibleId: String): List<BookDTO>

    @Query("SELECT * FROM ${BookDTO.TABLE_NAME} WHERE id = :bookId AND bibleId = :bibleId")
    fun getBook(bookId: String, bibleId: String): BookDTO

}
