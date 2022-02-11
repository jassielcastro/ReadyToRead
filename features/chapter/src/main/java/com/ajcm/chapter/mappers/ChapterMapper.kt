package com.ajcm.chapter.mappers

import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.entity.NextChapter
import com.ajcm.domain.entity.PreviousChapter
import com.ajcm.chapter.database.model.ChapterDTO
import javax.inject.Inject

class ChapterMapper @Inject constructor() : BaseMapper<Chapter, ChapterDTO> {

    override fun from(e: ChapterDTO): Chapter {
        return Chapter(
            e.id,
            e.bibleId,
            e.number,
            e.bookId,
            e.content ?: "",
            e.reference,
            e.verseCount ?: 0,
            e.next,
            e.previous,
            e.copyright ?: ""
        )
    }

    override fun to(t: Chapter): ChapterDTO {
        return ChapterDTO(
            t.id,
            t.bibleId,
            t.number,
            t.bookId,
            t.content,
            t.reference,
            t.verseCount,
            t.next ?: NextChapter(),
            t.previous ?: PreviousChapter(),
            t.copyright
        )
    }
}
