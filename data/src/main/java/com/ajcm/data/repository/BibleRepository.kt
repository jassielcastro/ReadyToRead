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
        var bibles = localDataSource.getBibles()
        if (bibles.isEmpty()) {
            localDataSource.saveBibles(remoteDataSource.getBibles())
            bibles = localDataSource.getBibles()
        }

        val req = request.query.lowercase()

        val sorted = if (req.isBlank()) {
            bibles.cut(request.size, request.orderBy)
        } else {
            bibles.filterAndCut(request.size, request.orderBy) {
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
        var bible = try {
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
            bible = localDataSource.getBible(bibleId)
        }

        return bible
    }

    override suspend fun toggleFavorite(bibleId: String): Bible {
        return localDataSource.toggleFavorite(bibleId)
    }
}
