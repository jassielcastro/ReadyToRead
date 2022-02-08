package com.kavak.chapter.database.converters

import androidx.room.TypeConverter
import com.ajcm.domain.entity.PreviousChapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreviousChapterConverter {

    @TypeConverter
    fun toString(chapter: PreviousChapter): String {
        return Gson().toJson(chapter)
    }

    @TypeConverter
    fun toObject(chapter: String): PreviousChapter {
        return Gson().fromJson(
            chapter,
            object : TypeToken<PreviousChapter>() {}.type
        )
    }

}
