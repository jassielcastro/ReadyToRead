package com.ajcm.domain.usecase.book

import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IBookRepository
import javax.inject.Inject

class GetBooksUc @Inject constructor(private val repository: IBookRepository) {
    suspend operator fun invoke(id: String): List<Book> {
        return repository.getBooks(id)
    }
}
