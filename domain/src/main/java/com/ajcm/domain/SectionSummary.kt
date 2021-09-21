package com.ajcm.domain

data class SectionSummary(
    val id: String,
    val bibleId: String,
    val bookId: String,
    val title: String,
    val firstVerseId: String,
    val lastVerseId: String,
    val firstVerseOrgId: String,
    val lastVerseOrgId: String,
)
