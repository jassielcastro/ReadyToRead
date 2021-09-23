package com.ajcm.usecase.book

import com.ajcm.data.repository.IAudioBookRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.Book
import javax.inject.Inject

class GetAudioBookUC @Inject constructor(private val repository: IAudioBookRepository) :
    BaseUseCaseWithParams<Book, String> {
    override suspend fun invoke(id: String, vararg params: String): Book {
        return repository.getBook(id, params[0])
    }
}
