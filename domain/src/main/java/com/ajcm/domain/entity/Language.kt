package com.ajcm.domain.entity

data class Language(
    val id: String,
    val name: String,
    val nameLocal: String,
    val script: String,
    val scriptDirection: String
) {
    class Builder {
        var id: String = ""
        var name: String = ""
        var nameLocal: String = ""
        var script: String = ""
        var scriptDirection: String = ""

        fun build() = Language(
            id,
            name,
            nameLocal,
            script,
            scriptDirection
        )
    }
}

fun Language(block: Language.Builder.() -> Unit): Language {
    return Language.Builder().apply(block).build()
}
