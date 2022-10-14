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
    pink: Color,
    pinkLight: Color,
    purple: Color,
    button: Color,
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
    var pink by mutableStateOf(pink)
        private set
    var pinkLight by mutableStateOf(pinkLight)
        private set
    var purple by mutableStateOf(purple)
        private set
    var button by mutableStateOf(button)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    private val colors: List<Color> by lazy {
        listOf(Purple, Pink, PinkLight, BlueLight, Blue)
    }

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecundary: Color = this.textSecundary,
        error: Color = this.error,
        background: Color = this.background,
        gray: Color = this.gray,
        pink: Color = this.pink,
        pinkLight: Color = this.pinkLight,
        purple: Color = this.purple,
        button: Color = this.button,
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
        pink = other.pink
        pinkLight = other.pinkLight
        button = other.button
        purple = other.purple
    }

    fun random(): Color {
        return colors.random()
    }
}

private val White = Color(0xFFFFFFFF)
private val Dark = Color(0xFF000000)
private val Gray = Color(0xFFF5F5F5)

private val Purple = Color(0xFFC5ADED)
private val Pink = Color(0xFFF8ABEB)
private val PinkLight = Color(0xFFFFC8DD)
private val BlueLight = Color(0xFFBCE0FF)
private val Blue = Color(0xFFA2D2FF)

fun lightColors(
    primary: Color = BlueLight,
    secondary: Color = Blue,
    textPrimary: Color = Dark,
    textSecondary: Color = White,
    error: Color = Dark,
    background: Color = White,
    gray: Color = Gray,
    pink: Color = Pink,
    pinkLight: Color = PinkLight,
    button: Color = Dark,
    purple: Color = Purple,
): BibleColors = BibleColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    gray = gray,
    pink = pink,
    pinkLight = pinkLight,
    purple = purple,
    button = button,
    isLight = true
)

val LocalLightColors = staticCompositionLocalOf { lightColors() }
