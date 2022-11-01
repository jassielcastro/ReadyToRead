package com.ajcm.domain.usecase.bible

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.repository.IBibleRepository
import javax.inject.Inject

class GetBiblesUc @Inject constructor(private val repository: IBibleRepository) {

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

    suspend fun toggleFavorite(bibleId: String): Bible {
        return repository.toggleFavorite(bibleId)
    }
}
