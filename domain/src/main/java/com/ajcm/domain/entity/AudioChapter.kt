package com.ajcm.domain.entity

data class AudioChapter(
    val id: String,
    val bibleId: String,
    val number: String,
    val bookId: String,
    val resourceUrl: String,
    val timeCode: TimeCode,
    val expiresAt: Int,
    val reference: String,
    val next: NextChapter,
    val previous: PreviousChapter,
    val copyright: String
)
