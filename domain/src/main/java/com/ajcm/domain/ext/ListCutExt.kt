package com.ajcm.domain.ext

fun <T> List<T>.filterAndCut(size: Int, filter: (T) -> Boolean): List<T> {
    val filterder = this.filter { filter(it) }
    return filterder.cut(size)
}

fun <T> List<T>.cut(size: Int): List<T> {
    return if (size == -1) this else this.take(size)
}
