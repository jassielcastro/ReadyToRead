package com.ajcm.bibles.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajcm.domain.entity.AudioBibleSummary
import com.ajcm.domain.entity.Country
import com.ajcm.domain.entity.Language

@Entity(tableName = "BibleSummary")
data class BibleSummaryDTO(
    @PrimaryKey
    val id: String,
    val dblId: String,
    val abbreviation: String?,
    val abbreviationLocal: String?,
    val language: Language,
    val countries: List<Country>,
    val name: String?,
    val nameLocal: String?,
    val description: String?,
    val descriptionLocal: String?,
    val relatedDbl: String?,
    val type: String?,
    val updatedAt: String?,
    val audioBibles: List<AudioBibleSummary>
)
