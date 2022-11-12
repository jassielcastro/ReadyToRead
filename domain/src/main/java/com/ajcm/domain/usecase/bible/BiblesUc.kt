package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.repository.IBibleRepository
import kotlinx.coroutines.flow.Flow
import java.lang.NullPointerException
import javax.inject.Inject

class BiblesUc @Inject constructor(private val repository: IBibleRepository) {

    private val neededLanguages = listOf("English", "Español")

    suspend fun getAll(request: GetBibleRequest = GetBibleRequest {}): List<Bible> =
        repository.getBibles(request)

    suspend fun getBibleLanguages(size: Int): List<String> {
        val currentlanguages = mutableListOf<String>()
        currentlanguages.addAll(
            getAll().map {
                it.language.name
            }.distinct().shuffled().take(size)
        )

        neededLanguages.forEach {
            if (!currentlanguages.contains(it)) {
                currentlanguages.add(it)
            }
        }
        return currentlanguages.reversed()
    }

    suspend fun getBible(bibleId: String?): Bible {
        if (bibleId == null) throw NullPointerException()
        return repository.getBible(bibleId)
    }

    suspend fun getFavorites(): Flow<List<Bible>> {
        return repository.getFavouriteBibles()
    }

    suspend fun toggleFavorite(bibleId: String): Bible {
        return repository.toggleFavorite(bibleId)
    }
}
