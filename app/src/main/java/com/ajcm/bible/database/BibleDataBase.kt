package com.ajcm.bible.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.converters.CountryConverter
import com.ajcm.bibles.database.converters.LanguageConverter
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.book.database.BookDAO
import com.ajcm.book.database.model.BookDTO

@Database(
    entities = [BibleDTO::class, BookDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LanguageConverter::class,
    CountryConverter::class
)
abstract class BibleDataBase : RoomDatabase() {

    abstract fun bibleDao(): BibleDAO

    abstract fun bookDao(): BookDAO

}
