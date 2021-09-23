package com.ajcm.usecase.book

import com.ajcm.data.repository.IBookRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.Book
import javax.inject.Inject

class GetBooksUc @Inject constructor(private val repository: IBookRepository) :
    BaseListUseCaseWithParams<Book, String> {
    override suspend fun invoke(id: String, vararg params: String): List<Book> {
        return repository.getBooks(id)
    }
}
