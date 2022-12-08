package com.ajcm.domain.entity

enum class LocalColors {
    Blue,
    BlueLight,
    Brown,
    BrownLight,
    Orange,
    OrangeLight,
    Green,
    Red
}

fun randomColor(): String {
    return LocalColors.values().random().name
}

fun String.transformToColor(): LocalColors = LocalColors.valueOf(this)
