package com.ajcm.bibles.database.converters

import androidx.room.TypeConverter
import com.ajcm.domain.entity.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguageConverter {

    @TypeConverter
    fun toString(lang: Language): String {
        return Gson().toJson(lang)
    }

    @TypeConverter
    fun toObject(lang: String): Language {
        return Gson().fromJson(
            lang,
            object : TypeToken<Language>() {}.type
        )
    }

}
