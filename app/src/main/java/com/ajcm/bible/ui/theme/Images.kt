package com.ajcm.bible.ui.theme

import com.ajcm.bible.R
import com.ajcm.domain.entity.LocalImages
import com.ajcm.domain.entity.toLocalImage

fun String.toLocalImage(): Int {
    return when(this.toLocalImage()) {
        LocalImages.BIBLIOPHILE -> R.drawable.ic_bibliophile_amico
        LocalImages.LOVER_PANA -> R.drawable.ic_book_lover_pana
        LocalImages.LOVER_RAFIKI -> R.drawable.ic_book_lover_rafiki
        LocalImages.EBOOK_PANA -> R.drawable.ic_ebook_pana
        LocalImages.EBOOK_RAFIKI -> R.drawable.ic_ebook_rafiki
    }
}
