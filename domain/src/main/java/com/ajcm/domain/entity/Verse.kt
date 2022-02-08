package com.ajcm.domain.entity

data class Verse(
    val id: String,
    val bibleId: String,
    val orgId: String,
    val bookId: String,
    val chapterId: String,
    val content: String,
    val reference: String,
    val verseCount: Int,
    val copyright: String,
    val next: NextChapter?,
    val previous: PreviousChapter?
)
