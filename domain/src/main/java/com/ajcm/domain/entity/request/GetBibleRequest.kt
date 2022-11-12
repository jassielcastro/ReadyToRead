package com.ajcm.domain.entity.request

class GetBibleRequest internal constructor(
    val query: String,
    val size: Int,
    val sortedBy: OrderType
) {
    class Builder {
        var query: String = ""
        var size: Int = SIZE_ALL
        var ortedBy: OrderType = OrderType.DESC

        fun build() = GetBibleRequest(query, size, ortedBy)
    }

    companion object {
        const val SIZE_ALL = -1
    }

    enum class OrderType {
        DESC, RANDOM
    }
}

fun GetBibleRequest(block: GetBibleRequest.Builder.() -> Unit): GetBibleRequest {
    return GetBibleRequest.Builder().apply(block).build()
}
