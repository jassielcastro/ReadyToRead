package com.ajcm.book.mappers

import com.ajcm.book.database.model.BookDTO
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Book
import javax.inject.Inject

class BookMapper @Inject constructor() : BaseMapper<Book, BookDTO> {

    override fun from(e: BookDTO): Book {
        return Book(
            e.id,
            e.bibleId,
            e.abbreviation,
            e.name,
            e.nameLong
        )
    }

    override fun to(t: Book): BookDTO {
        return BookDTO(
            t.id,
            t.bibleId,
            t.abbreviation,
            t.name,
            t.nameLong
        )
    }
}
