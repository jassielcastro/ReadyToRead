package com.ajcm.bibles.datasource

import com.ajcm.bibles.database.BibleDAO
import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.bibles.database.model.BibleSummaryDTO
import com.ajcm.data.datasource.ILocalBibleDataSource
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class LocalBibleDataSource @Inject constructor(
    private val bibleDAO: BibleDAO,
    private val bibleSummaryMapper: BaseMapper<BibleSummary, BibleSummaryDTO>,
    private val bibleMapper: BaseMapper<Bible, BibleDTO>
) : ILocalBibleDataSource {

    override suspend fun saveBibles(bibleList: List<BibleSummary>) {
        bibleDAO.insertBibles(bibleList.map { bibleSummaryMapper.to(it) })
    }

    override suspend fun saveBible(bible: Bible) {
        bibleDAO.insertBible(bibleMapper.to(bible))
    }

    override suspend fun getBibles(): List<BibleSummary> {
        return bibleDAO.getAllBibles().map { bibleSummaryMapper.from(it) }
    }

    override suspend fun getBible(bibleId: String): Bible {
        return bibleMapper.from(bibleDAO.getBible(bibleId))
    }
}
