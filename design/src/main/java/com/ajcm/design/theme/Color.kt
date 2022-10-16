package com.ajcm.design.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class BibleColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    error: Color,
    background: Color,
    gray: Color,
    orange: Color,
    orangeLight: Color,
    brownLight: Color,
    button: Color,
    white: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecundary by mutableStateOf(textSecondary)
        private set
    var error by mutableStateOf(error)
        private set
    var background by mutableStateOf(background)
        private set
    var gray by mutableStateOf(gray)
        private set
    var orange by mutableStateOf(orange)
        private set
    var orangeLight by mutableStateOf(orangeLight)
        private set
    var brownLight by mutableStateOf(brownLight)
        private set
    var button by mutableStateOf(button)
        private set
    var white by mutableStateOf(white)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    private val colors: List<Color> by lazy {
        listOf(
            Orange,
            OrangeLight,
            Orange_1,
            Orange_2,
            Orange_3,
            Orange_4,
            Orange_5,
            Orange_6
        )
    }

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecundary: Color = this.textSecundary,
        error: Color = this.error,
        background: Color = this.background,
        gray: Color = this.gray,
        pink: Color = this.orange,
        pinkLight: Color = this.orangeLight,
        purple: Color = this.brownLight,
        button: Color = this.button,
        white: Color = this.white,
        isLight: Boolean = this.isLight
    ): BibleColors = BibleColors(
        primary,
        secondary,
        textPrimary,
        textSecundary,
        error,
        background,
        gray,
        pink,
        pinkLight,
        purple,
        button,
        white,
        isLight
    )

    fun updateColorsFrom(other: BibleColors) {
        primary = other.primary
        secondary = other.secondary
        textPrimary = other.textPrimary
        textSecundary = other.textSecundary
        background = other.background
        error = other.error
        isLight = other.isLight
        gray = other.gray
        orange = other.orange
        orangeLight = other.orangeLight
        button = other.button
        white = other.white
        brownLight = other.brownLight
    }

    fun random(): Color {
        return colors.random()
    }
}

private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val Gray = Color(0xFFF5F5F5)

private val Brown = Color(0xFFB79F95)
private val BrownDark = Color(0xFF877167)
private val BrownLight = Color(0xFFEBE2DE)
private val Orange = Color(0xFFFFA000)
private val OrangeLight = Color(0xFFFFD184)

// items colors
private val Orange_1 = Color(0xFFFFA000)
private val Orange_2 = Color(0xFFFFD149)
private val Orange_3 = Color(0xFFEEAC71)
private val Orange_4 = Color(0xFFFFE0B2)
private val Orange_5 = Color(0xFFFF833A)
private val Orange_6 = Color(0xFFEC933B)

fun lightColors(
    primary: Color = BrownDark,
    secondary: Color = Brown,
    textPrimary: Color = Black,
    textSecondary: Color = White,
    error: Color = Black,
    background: Color = White,
    gray: Color = Gray,
    orange: Color = Orange,
    orangeLight: Color = OrangeLight,
    button: Color = Orange,
    white: Color = White,
    brownLight: Color = BrownLight,
): BibleColors = BibleColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    gray = gray,
    orange = orange,
    orangeLight = orangeLight,
    brownLight = brownLight,
    button = button,
    white = white,
    isLight = true
)

val LocalLightColors = staticCompositionLocalOf { lightColors() }
