package com.ajcm.domain.entity.request

class GetBibleRequest internal constructor(
    val query: String,
    val size: Int,
    val favorite: Favorite
) {
    class Builder {
        var query: String = ""
        var size: Int = SIZE_ALL
        var favorite: Favorite = Favorite.ALL

        fun build() = GetBibleRequest(query, size, favorite)
    }

    companion object {
        const val SIZE_ALL = -1
    }

    enum class Favorite {
        ALL, TRUE
    }
}

fun GetBibleRequest(block: GetBibleRequest.Builder.() -> Unit): GetBibleRequest {
    return GetBibleRequest.Builder().apply(block).build()
}
