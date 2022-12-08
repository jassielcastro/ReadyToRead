package com.ajcm.domain.entity

enum class LocalImages {
    BIBLIOPHILE,
    LOVER_PANA,
    LOVER_RAFIKI,
    EBOOK_PANA,
    EBOOK_RAFIKI
}

fun randomImage(): String {
    return LocalImages.values().random().name
}

fun String.toLocalImage(): LocalImages = LocalImages.valueOf(this)
