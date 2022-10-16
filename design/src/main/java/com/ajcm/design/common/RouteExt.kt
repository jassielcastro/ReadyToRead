package com.ajcm.design.common

fun String?.cleanRoute(): String? {
    return this?.split("/".toRegex())?.firstOrNull()
}
