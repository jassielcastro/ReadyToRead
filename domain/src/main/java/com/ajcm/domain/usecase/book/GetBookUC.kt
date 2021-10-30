package com.ajcm.domain.usecase.book

import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import javax.inject.Inject

class GetBookUC @Inject constructor(private val repository: IBookRepository) {
    suspend operator fun invoke(id: String, bookId: String): Book {
        return repository.getBook(id, bookId)
    }
}
