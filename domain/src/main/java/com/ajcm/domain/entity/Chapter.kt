package com.ajcm.domain.entity

data class Chapter(
    val id: String,
    val bibleId: String,
    val number: String,
    val bookId: String,
    val content: String,
    val reference: String,
    val verseCount: Int,
    var next: NextChapter?,
    var previous: PreviousChapter?,
    var copyright: String
)
