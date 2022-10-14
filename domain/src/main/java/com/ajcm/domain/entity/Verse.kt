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
) {
    class Builder {
        var id: String = ""
        var bibleId: String = ""
        var orgId: String = ""
        var bookId: String = ""
        var chapterId: String = ""
        var content: String = ""
        var reference: String = ""
        var verseCount: Int = 0
        var copyright: String = ""
        var next: NextChapter? = null
        var previous: PreviousChapter? = null

        fun build() = Verse(
            id,
            bibleId,
            orgId,
            bookId,
            chapterId,
            content,
            reference,
            verseCount,
            copyright,
            next,
            previous
        )
    }
}

fun Verse(block: Verse.Builder.() -> Unit) = Verse.Builder().apply(block).build()
