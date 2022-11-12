package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.ext.cut
import com.ajcm.domain.ext.filterAndCut
import com.ajcm.domain.repository.IBibleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BibleRepository @Inject constructor(
    private val localDataSource: ILocalBibleDataSource,
    private val remoteDataSource: IRemoteBibleDataSource
) : IBibleRepository {

    override suspend fun getBibles(request: GetBibleRequest): List<Bible> {
        if (localDataSource.getBibles().isEmpty()) {
            val bibles = remoteDataSource.getBibles()
            localDataSource.saveBibles(bibles)
        }

        val bibles = localDataSource.getBibles()
        val req = request.query.lowercase()

        val sorted = if (req.isBlank()) {
            bibles.cut(request.size, request.sortedBy)
        } else {
            bibles.filterAndCut(request.size, request.sortedBy) {
                it.name.lowercase().contains(req)
                        || it.nameLocal.lowercase().contains(req)
                        || it.language.name.lowercase().contains(req)
                        || it.language.nameLocal.lowercase().contains(req)
                        || it.countries.map { c -> c.name.lowercase().contains(req) || c.nameLocal.lowercase().contains(req) }.any { lang -> lang }
            }
        }

        return sorted
    }

    override suspend fun getFavouriteBibles(): Flow<List<Bible>> {
        return localDataSource.getFavouriteBibles()
    }

    override suspend fun getBible(bibleId: String): Bible {
        val bible = try {
            localDataSource.getBible(bibleId)
        } catch (e: Exception) {
            remoteDataSource.getBible(bibleId)
        }

        if (bible.info.isEmpty()) {
            val completeBible = remoteDataSource.getBible(bibleId).apply {
                image = bible.image
                color = bible.color
            }
            localDataSource.saveBible(completeBible)
        }

        return localDataSource.getBible(bibleId)
    }

    override suspend fun toggleFavorite(bibleId: String): Bible {
        return localDataSource.toggleFavorite(bibleId)
    }
}
