package com.ajcm.domain.entity

data class Section(
    val id: String,
    val bibleId: String,
    val bookId: String,
    val chapterId: String,
    val title: String,
    val content: String,
    val verseCount: Int,
    val firstVerseId: String,
    val lastVerseId: String,
    val firstVerseOrgId: String,
    val lastVerseOrgId: String,
    val copyright: String,
    val next: NextChapter,
    val previous: PreviousChapter
)
