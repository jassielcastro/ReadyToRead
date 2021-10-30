package com.ajcm.domain.entity

data class SearchVerse(
    val id: String,
    val orgId: String,
    val bibleId: String,
    val bookId: String,
    val chapterId: String,
    val text: String,
    val reference: String
)
