package com.ajcm.bibles.datasource

import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalBibleDataSource @Inject constructor(
    private val bibleDAO: BibleDAO,
    private val bibleMapper: BaseMapper<Bible, BibleDTO>
) : ILocalBibleDataSource {

    override suspend fun saveBibles(bibleList: List<Bible>) {
        bibleDAO.insertBibles(bibleList.map { bibleMapper.to(it) })
    }

    override suspend fun saveBible(bible: Bible) {
        bibleDAO.insertOrUpdateBible(bibleMapper.to(bible))
    }

    override suspend fun getBibles(): List<Bible> {
        return bibleDAO.getAllBibles().map { bibleMapper.from(it) }
    }

    override suspend fun getFavouriteBibles(): Flow<List<Bible>> {
        return bibleDAO.getFavouriteBibles().map {
            it.map { b -> bibleMapper.from(b) }
        }
    }

    override suspend fun getBible(bibleId: String): Bible {
        return bibleMapper.from(bibleDAO.getBible(bibleId))
    }

    override suspend fun toggleFavorite(bibleId: String): Bible {
        return bibleMapper.from(bibleDAO.toggleFavoriteAndGet(bibleId))
    }
}
