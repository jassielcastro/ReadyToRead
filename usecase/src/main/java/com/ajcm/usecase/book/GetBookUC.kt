package com.ajcm.usecase.book

import com.ajcm.annotation.BookRepo
import com.ajcm.data.repository.IBookRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Book
import javax.inject.Inject

class GetBookUC @Inject constructor(@BookRepo private val repository: IBookRepository) : BaseUseCaseWithParams<Book, String> {
    override suspend fun invoke(vararg params: String): Book {
        return repository.getBook(params[0], params[1])
    }
}
