package com.ajcm.domain.entity

data class BibleSummary(
    val id: String,
    val dblId: String,
    val abbreviation: String,
    val abbreviationLocal: String,
    val language: Language,
    val countries: List<Country>,
    val name: String,
    val nameLocal: String,
    val description: String,
    val descriptionLocal: String,
    val relatedDbl: String,
    val type: String,
    val updatedAt: String,
    val audioBibles: List<AudioBibleSummary>
)
