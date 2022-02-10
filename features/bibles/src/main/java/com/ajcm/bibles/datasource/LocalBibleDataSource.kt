package com.ajcm.bibles.datasource

import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import javax.inject.Inject

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

    override suspend fun getFavouriteBibles(): List<Bible> {
        return bibleDAO.getFavouriteBibles().map { bibleMapper.from(it) }
    }

    override suspend fun getBible(bibleId: String): Bible {
        return bibleMapper.from(bibleDAO.getBible(bibleId))
    }
}
