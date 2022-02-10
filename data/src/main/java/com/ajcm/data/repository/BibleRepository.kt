package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.repository.IBibleRepository
import javax.inject.Inject

class BibleRepository @Inject constructor(
    private val localDataSource: ILocalBibleDataSource,
    private val remoteDataSource: IRemoteBibleDataSource
) : IBibleRepository {

    override suspend fun getBibles(): List<Bible> {
        if (localDataSource.getBibles().isEmpty()) {
            val bibles = remoteDataSource.getBibles()
            localDataSource.saveBibles(bibles)
        }

        return localDataSource.getBibles()
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
