package com.ajcm.bibles.database.converters

import androidx.room.TypeConverter
import com.ajcm.domain.entity.AudioBibleSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AudioBibleSummaryConverter {

    @TypeConverter
    fun toString(audioBibleSummary: List<AudioBibleSummary>): String {
        return Gson().toJson(audioBibleSummary)
    }

    @TypeConverter
    fun toObject(audioBibleSummary: String): List<AudioBibleSummary> {
        return Gson().fromJson(
            audioBibleSummary,
            object : TypeToken<List<AudioBibleSummary>>() {}.type
        )
    }

}
