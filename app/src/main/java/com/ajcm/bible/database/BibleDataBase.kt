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
import com.ajcm.chapter.database.ChapterDAO
import com.ajcm.chapter.database.converters.NextChapterConverter
import com.ajcm.chapter.database.converters.PreviousChapterConverter
import com.ajcm.chapter.database.model.ChapterDTO
import com.ajcm.configuration.database.ConfigurationDAO
import com.ajcm.configuration.database.model.ConfigurationDTO
import com.ajcm.verse.database.VerseDAO
import com.ajcm.verse.database.model.VerseDTO

@Database(
    entities = [BibleDTO::class, BookDTO::class, ChapterDTO::class, VerseDTO::class, ConfigurationDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LanguageConverter::class,
    CountryConverter::class,
    NextChapterConverter::class,
    PreviousChapterConverter::class,
)
abstract class BibleDataBase : RoomDatabase() {

    abstract fun bibleDao(): BibleDAO

    abstract fun bookDao(): BookDAO

    abstract fun chapterDao(): ChapterDAO

    abstract fun verseDao(): VerseDAO

    abstract fun configurationDao(): ConfigurationDAO

}
