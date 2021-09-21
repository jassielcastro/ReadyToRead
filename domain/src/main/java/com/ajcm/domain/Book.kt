package com.ajcm.domain

data class Book(
    val id: String,
    val bibleId: String,
    val abbreviation: String,
    val name: String,
    val nameLong: String,
    val chapters: List<ChapterSummary>
)
