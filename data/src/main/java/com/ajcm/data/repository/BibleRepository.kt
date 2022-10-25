package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.ext.cut
import com.ajcm.domain.ext.filterAndCut
import com.ajcm.domain.repository.IBibleRepository
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
        val req = request.query

        return if (req.isBlank()) {
            bibles.cut(request.size)
        } else {
            bibles.filterAndCut(request.size) {
                it.name.contains(req, ignoreCase = true)
                        || it.nameLocal.contains(req, ignoreCase = true)
                        || it.language.name.contains(req, ignoreCase = true)
                        || it.language.nameLocal.contains(req, ignoreCase = true)
                        || it.countries.map { c -> c.name.contains(req) || c.nameLocal.contains(req) }.any { lang -> lang }
            }
        }
    }

    override suspend fun getFavouriteBibles(): List<Bible> {
        return localDataSource.getFavouriteBibles()
    }

    override suspend fun getBible(bibleId: String): Bible {
        val bible = try {
            localDataSource.getBible(bibleId)
        } catch (e: Exception) {
            remoteDataSource.getBible(bibleId)
        }

        if (bible.info.isEmpty()) {
            localDataSource.saveBible(remoteDataSource.getBible(bibleId))
        }

        return localDataSource.getBible(bibleId)
    }
}
