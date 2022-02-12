package com.ajcm.bibles.datasource

import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.common.annotation.IoDispatcher
import com.ajcm.common.annotation.MainScope
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class LocalBibleDataSource @Inject constructor(
    private val bibleDAO: BibleDAO,
    private val bibleMapper: BaseMapper<Bible, BibleDTO>,
    @MainScope
    private val mainScope: CoroutineScope,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) : ILocalBibleDataSource {

    override suspend fun saveBibles(bibleList: List<Bible>) {
        mainScope.launch {
            withContext(ioDispatcher) {
                bibleDAO.insertBibles(bibleList.map { bibleMapper.to(it) })
            }
        }
    }

    override suspend fun saveBible(bible: Bible) {
        mainScope.launch {
            withContext(ioDispatcher) {
                bibleDAO.insertOrUpdateBible(bibleMapper.to(bible))
            }
        }
    }

    override suspend fun getBibles(): List<Bible> {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val bibles = withContext(ioDispatcher) {
                    bibleDAO.getAllBibles().map { bibleMapper.from(it) }
                }
                continuation.resume(bibles)
            }
        }
    }

    override suspend fun getFavouriteBibles(): List<Bible> {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val bibles = withContext(ioDispatcher) {
                    bibleDAO.getFavouriteBibles().map { bibleMapper.from(it) }
                }
                continuation.resume(bibles)
            }
        }
    }

    override suspend fun getBible(bibleId: String): Bible {
        return suspendCoroutine { continuation ->
            mainScope.launch {
                val bible = withContext(ioDispatcher) {
                    bibleMapper.from(bibleDAO.getBible(bibleId))
                }
                continuation.resume(bible)
            }
        }
    }
}
