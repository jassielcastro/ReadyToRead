package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.datasource.IRemoteBibleDataSource
import com.ajcm.domain.repository.IBibleRepository
import com.ajcm.domain.entity.Bible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    override suspend fun getBible(bibleId: String): Bible = suspendCoroutine { continuation ->
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                localDataSource.getBible(bibleId)
            }.onSuccess {
                continuation.resume(it)
            }.onFailure {
                val bible = remoteDataSource.getBible(bibleId)
                localDataSource.saveBible(bible)
                continuation.resume(localDataSource.getBible(bibleId))
            }
        }
    }
}
