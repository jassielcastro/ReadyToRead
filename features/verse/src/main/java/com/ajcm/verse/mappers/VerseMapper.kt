package com.ajcm.verse.mappers

import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.NextChapter
import com.ajcm.domain.entity.PreviousChapter
import com.ajcm.domain.entity.Verse
import com.ajcm.verse.database.model.VerseDTO
import javax.inject.Inject

class VerseMapper @Inject constructor() : BaseMapper<Verse, VerseDTO> {

    override fun from(e: VerseDTO): Verse {
        return Verse(
            e.id,
            e.orgId,
            e.bibleId,
            e.bookId,
            e.chapterId,
            e.content ?: "",
            e.reference ?: "",
            e.verseCount ?: 0,
            e.copyright ?: "",
            e.next ?: NextChapter(),
            e.previous ?: PreviousChapter(),
        )
    }

    override fun to(t: Verse): VerseDTO {
        return VerseDTO(
            t.id,
            t.orgId,
            t.bibleId,
            t.bookId,
            t.chapterId,
            t.content,
            t.reference,
            t.verseCount,
            t.copyright,
            t.next ?: NextChapter(),
            t.previous ?: PreviousChapter(),
        )
    }
}
