package com.ajcm.domain.ext

import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest

fun List<Bible>.filterAndCut(size: Int, orderType: GetBibleRequest.OrderType, filter: (Bible) -> Boolean): List<Bible> {
    val filterder = this.filter { filter(it) }
    return filterder.cut(size, orderType)
}

fun List<Bible>.cut(size: Int, orderType: GetBibleRequest.OrderType): List<Bible> {
    val sortedBibles = when (orderType) {
        GetBibleRequest.OrderType.DESC -> {
            this.sortedBy { it.nameLocal }
        }
        GetBibleRequest.OrderType.RANDOM -> {
            this.shuffled()
        }
    }
    return if (size == -1) sortedBibles else sortedBibles.take(size)
}
