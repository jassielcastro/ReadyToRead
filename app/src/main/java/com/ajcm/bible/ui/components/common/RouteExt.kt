package com.ajcm.bible.ui.components.common

fun String?.cleanRoute(): String? {
    return this?.split("/".toRegex())?.firstOrNull()
}
