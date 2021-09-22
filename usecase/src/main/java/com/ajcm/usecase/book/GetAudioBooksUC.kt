package com.ajcm.usecase.book

import com.ajcm.annotation.AudioBookRepo
import com.ajcm.data.repository.IBookRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.Book
import javax.inject.Inject

class GetAudioBooksUC @Inject constructor(@AudioBookRepo private val repository: IBookRepository) :
    BaseListUseCaseWithParams<Book, String> {
    override suspend fun invoke(vararg params: String): List<Book> {
        return repository.getBooks(params[0])
    }
}
