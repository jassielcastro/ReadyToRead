package com.ajcm.chapter.database.converters

import androidx.room.TypeConverter
import com.ajcm.domain.entity.NextChapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NextChapterConverter {

    @TypeConverter
    fun toString(chapter: NextChapter): String {
        return Gson().toJson(chapter)
    }

    @TypeConverter
    fun toObject(chapter: String): NextChapter {
        return Gson().fromJson(
            chapter,
            object : TypeToken<NextChapter>() {}.type
        )
    }

}
