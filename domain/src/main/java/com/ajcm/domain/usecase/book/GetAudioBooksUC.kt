package com.ajcm.domain.usecase.book

import com.ajcm.domain.entity.Book
import com.ajcm.domain.repository.IAudioBookRepository
import javax.inject.Inject

class GetAudioBooksUC @Inject constructor(private val repository: IAudioBookRepository) {
    suspend operator fun invoke(id: String): List<Book> {
        return repository.getBooks(id)
    }
}
