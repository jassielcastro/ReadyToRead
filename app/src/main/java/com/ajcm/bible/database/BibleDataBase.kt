package com.ajcm.bible.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.converters.AudioBibleSummaryConverter
import com.ajcm.bibles.database.converters.CountryConverter
import com.ajcm.bibles.database.converters.LanguageConverter
import com.ajcm.bibles.database.model.*

@Database(
    entities = [BibleDTO::class, BibleSummaryDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LanguageConverter::class,
    CountryConverter::class,
    AudioBibleSummaryConverter::class
)
abstract class BibleDataBase : RoomDatabase() {

    abstract fun bibleDao(): BibleDAO

}
