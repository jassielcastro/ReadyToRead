package com.ajcm.design.theme

import com.ajcm.design.R

enum class Images(val resource: Int) {
    BIBLIOPHILE(R.drawable.ic_bibliophile_amico),
    LOVER_PANA(R.drawable.ic_book_lover_pana),
    LOVER_RAFIKI(R.drawable.ic_book_lover_rafiki),
    EBOOK_PANA(R.drawable.ic_ebook_pana),
    EBOOK_RAFIKI(R.drawable.ic_ebook_rafiki)
}

fun randomImage(): String {
    return Images.values().random().name
}

fun String.transformToImage(): Images = Images.valueOf(this)
