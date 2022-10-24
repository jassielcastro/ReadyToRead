package com.ajcm.domain.entity.request

class GetBibleRequest internal constructor(
    val query: String,
    val size: Int
) {
    class Builder {
        var query: String = ""
        var size: Int = SIZE_ALL

        fun build() = GetBibleRequest(query, size)
    }

    companion object {
        const val SIZE_ALL = -1
    }
}

fun GetBibleRequest(block: GetBibleRequest.Builder.() -> Unit): GetBibleRequest {
    return GetBibleRequest.Builder().apply(block).build()
}
