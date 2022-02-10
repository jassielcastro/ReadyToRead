package com.ajcm.domain.entity

data class Bible(
    val id: String,
    val dblId: String,
    val abbreviation: String,
    val abbreviationLocal: String,
    val copyright: String,
    val language: Language,
    val countries: List<Country>,
    val name: String,
    val nameLocal: String,
    val description: String,
    val descriptionLocal: String,
    val info: String,
    val type: String,
    val updatedAt: String,
    val relatedDbl: String,
    var isFavourite: Boolean
)
