package com.ajcm.bibles.mappers

import com.ajcm.bibles.database.model.BibleSummaryDTO
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.BibleSummary
import javax.inject.Inject

class BibleSummaryMapper @Inject constructor() : BaseMapper<BibleSummary, BibleSummaryDTO> {

    override fun from(e: BibleSummaryDTO): BibleSummary {
        return BibleSummary(
            id = e.id,
            dblId = e.dblId,
            abbreviation = e.abbreviation ?: "",
            abbreviationLocal = e.abbreviationLocal ?: "",
            language = e.language,
            countries = e.countries,
            name = e.name ?: "",
            nameLocal = e.nameLocal ?: "",
            description = e.description ?: "",
            descriptionLocal = e.descriptionLocal ?: "",
            relatedDbl = e.relatedDbl ?: "",
            type = e.type ?: "",
            updatedAt = e.updatedAt ?: "",
            audioBibles = e.audioBibles
        )
    }

    override fun to(t: BibleSummary): BibleSummaryDTO {
        return BibleSummaryDTO(
            id = t.id,
            dblId = t.dblId,
            abbreviation = t.abbreviation,
            abbreviationLocal = t.abbreviationLocal,
            language = t.language,
            countries = t.countries,
            name = t.name,
            nameLocal = t.nameLocal,
            description = t.description,
            descriptionLocal = t.descriptionLocal,
            relatedDbl = t.relatedDbl,
            type = t.type,
            updatedAt = t.updatedAt,
            audioBibles = t.audioBibles
        )
    }
}
