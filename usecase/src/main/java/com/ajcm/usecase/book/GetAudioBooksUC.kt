package com.ajcm.usecase.book

import com.ajcm.data.repository.IAudioBookRepository
import com.ajcm.data.usecase.BaseListUseCaseWithParams
import com.ajcm.domain.Book
import javax.inject.Inject

class GetAudioBooksUC @Inject constructor(private val repository: IAudioBookRepository) :
    BaseListUseCaseWithParams<Book, String> {
    override suspend fun invoke(id: String, vararg params: String): List<Book> {
        return repository.getBooks(id)
    }
}
