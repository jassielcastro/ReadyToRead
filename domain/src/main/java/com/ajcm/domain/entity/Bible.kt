package com.ajcm.domain.entity

data class Bible(
    val id: String,
    val dblId: String,
    val abbreviation: String,
    val abbreviationLocal: String,
    val copyright: String,
    val language: Language,
    val countries: List<Country>,
    val name: String,
    val nameLocal: String,
    val description: String,
    val descriptionLocal: String,
    val info: String,
    val type: String,
    val updatedAt: String,
    val relatedDbl: String,
    val isFavourite: Boolean,
    val color: Int,
    val image: String
) {

    fun getRegions(): String =
        countries.joinToString(separator = ", ") { it.name }
            .plus(" - ${language.nameLocal}")

    fun fullInformation(): String {
        return "$info \n $descriptionLocal"
    }

    class Builder {
        var id: String = ""
        var dblId: String = ""
        var abbreviation: String = ""
        var abbreviationLocal: String = ""
        var copyright: String = ""
        var language: Language = Language {}
        var countries: List<Country> = emptyList()
        var name: String = ""
        var nameLocal: String = ""
        var description: String = ""
        var descriptionLocal: String = ""
        var info: String = ""
        var type: String = ""
        var updatedAt: String = ""
        var relatedDbl: String = ""
        var isFavourite: Boolean = false
        var color: Int = 0
        var image: String = ""

        fun build() = Bible(
            id,
            dblId,
            abbreviation,
            abbreviationLocal,
            copyright,
            language,
            countries,
            name,
            nameLocal,
            description,
            descriptionLocal,
            info,
            type,
            updatedAt,
            relatedDbl,
            isFavourite,
            color,
            image
        )
    }
}

fun Bible(block: Bible.Builder.() -> Unit): Bible {
    return Bible.Builder().apply(block).build()
}
