package com.ajcm.bibles.database.converters

import androidx.room.TypeConverter
import com.ajcm.domain.entity.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryConverter {

    @TypeConverter
    fun toString(country: List<Country>): String {
        return Gson().toJson(country)
    }

    @TypeConverter
    fun toObject(country: String): List<Country> {
        return Gson().fromJson(
            country,
            object : TypeToken<List<Country>>() {}.type
        )
    }

}
