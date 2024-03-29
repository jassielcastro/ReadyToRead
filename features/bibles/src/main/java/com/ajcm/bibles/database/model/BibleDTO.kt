package com.ajcm.bibles.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcm.domain.entity.Country
import com.ajcm.domain.entity.Language

@Entity(tableName = BibleDTO.TABLE_NAME)
data class BibleDTO(
    @PrimaryKey
    val id: String,
    val dblId: String,
    val copyright: String?,
    val language: Language,
    val countries: List<Country>,
    val name: String?,
    val nameLocal: String?,
    val description: String?,
    val descriptionLocal: String?,
    val info: String?,
    val type: String?,
    var isFavourite: Int,
    var color: String,
    var image: String
) {
    companion object {
        const val TABLE_NAME = "Bible"
    }
}
