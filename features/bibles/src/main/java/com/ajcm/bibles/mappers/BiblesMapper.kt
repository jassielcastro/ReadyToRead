package com.ajcm.bibles.mappers

import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import javax.inject.Inject

class BiblesMapper @Inject constructor() : BaseMapper<Bible, BibleDTO> {

    override fun from(e: BibleDTO): Bible {
        return Bible(
            id = e.id,
            dblId = e.dblId,
            abbreviation = e.abbreviation ?: "",
            abbreviationLocal = e.abbreviationLocal ?: "",
            copyright = e.copyright ?: "",
            language = e.language,
            countries = e.countries,
            name = e.name ?: "",
            nameLocal = e.nameLocal ?: "",
            description = e.description ?: "",
            descriptionLocal = e.descriptionLocal ?: "",
            info = e.info ?: "",
            type = e.type ?: "",
            updatedAt = e.updatedAt ?: "",
            relatedDbl = e.relatedDbl ?: "",
            isFavourite = e.isFavourite == 1
        )
    }

    override fun to(t: Bible): BibleDTO {
        return BibleDTO(
            id = t.id,
            dblId = t.dblId,
            abbreviation = t.abbreviation,
            abbreviationLocal = t.abbreviationLocal,
            copyright = t.copyright,
            language = t.language,
            countries = t.countries,
            name = t.name,
            nameLocal = t.nameLocal,
            description = t.description,
            descriptionLocal = t.descriptionLocal,
            info = t.info,
            type = t.type,
            updatedAt = t.updatedAt,
            relatedDbl = t.relatedDbl,
            isFavourite = if (t.isFavourite) 1 else 0
        )
    }

}
