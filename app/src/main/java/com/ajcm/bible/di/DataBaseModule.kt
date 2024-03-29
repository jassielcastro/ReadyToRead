package com.ajcm.bible.di

import android.content.Context
import androidx.room.Room
import com.ajcm.bible.database.BibleDataBase
import com.ajcm.bibles.database.BibleDAO
import com.ajcm.book.database.BookDAO
import com.ajcm.chapter.database.ChapterDAO
import com.ajcm.configuration.database.ConfigurationDAO
import com.ajcm.verse.database.VerseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): BibleDataBase {
        return Room.databaseBuilder(
            context,
            BibleDataBase::class.java,
            "bibles_db_ajcm"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBibleDao(database: BibleDataBase): BibleDAO {
        return database.bibleDao()
    }

    @Provides
    fun provideBookDao(database: BibleDataBase): BookDAO {
        return database.bookDao()
    }

    @Provides
    fun provideChapterDao(database: BibleDataBase): ChapterDAO {
        return database.chapterDao()
    }

    @Provides
    fun provideVerseDao(database: BibleDataBase): VerseDAO {
        return database.verseDao()
    }

    @Provides
    fun provideConfigurationDao(database: BibleDataBase): ConfigurationDAO {
        return database.configurationDao()
    }

}
