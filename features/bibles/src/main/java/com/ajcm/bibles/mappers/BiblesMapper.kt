package com.ajcm.bibles.mappers

import com.ajcm.bibles.database.model.BibleDTO
import com.ajcm.data.mapper.BaseMapper
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.randomColor
import com.ajcm.domain.entity.randomImage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BiblesMapper @Inject constructor() : BaseMapper<Bible, BibleDTO> {

    override fun from(e: BibleDTO): Bible {
        return Bible(
            id = e.id,
            dblId = e.dblId,
            copyright = e.copyright ?: "",
            language = e.language,
            countries = e.countries,
            name = e.name ?: "",
            nameLocal = e.nameLocal ?: "",
            description = e.description ?: "",
            descriptionLocal = e.descriptionLocal ?: "",
            info = e.info ?: "",
            type = e.type ?: "",
            isFavourite = e.isFavourite == 1,
            color = e.color,
            image = e.image
        )
    }

    override fun to(t: Bible): BibleDTO {
        return BibleDTO(
            id = t.id,
            dblId = t.dblId,
            copyright = t.copyright,
            language = t.language,
            countries = t.countries,
            name = t.name,
            nameLocal = t.nameLocal,
            description = t.description,
            descriptionLocal = t.descriptionLocal,
            info = t.info,
            type = t.type,
            isFavourite = if (t.isFavourite) 1 else 0,
            color = if (t.color.isNullOrEmpty()) randomColor() else t.color,
            image = if (t.image.isNullOrEmpty()) randomImage() else t.image
        )
    }
}
