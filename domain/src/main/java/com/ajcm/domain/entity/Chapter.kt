package com.ajcm.domain.entity

data class Chapter(
    val id: String,
    val bibleId: String,
    val number: String,
    val bookId: String,
    val content: String,
    val reference: String,
    val verseCount: Int,
    val next: NextChapter?,
    val previous: PreviousChapter?,
    val copyright: String
) {

    fun getVerses(): List<String> {
        return content.formateVerse()
            .map {
                it.replaceFirst("\\s".toRegex(), "")
            }
    }

    private fun String.formateVerse(): List<String> {
        return this.split("\\s\\[\\d*\\]".toRegex()).filter {
            it.trim().isNotEmpty()
        }
    }

    class Builder {
        var id: String = ""
        var bibleId: String = ""
        var number: String = ""
        var bookId: String = ""
        var content: String = ""
        var reference: String = ""
        var verseCount: Int = 0
        var next: NextChapter? = null
        var previous: PreviousChapter? = null
        var copyright: String = ""

        fun build() = Chapter(
            id,
            bibleId,
            number,
            bookId,
            content,
            reference,
            verseCount,
            next,
            previous,
            copyright
        )
    }
}

fun Chapter(block: Chapter.Builder.() -> Unit): Chapter {
    return Chapter.Builder().apply(block).build()
}
