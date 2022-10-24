package com.ajcm.design.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class BibleColors(
    blue: Color,
    blueLight: Color,
    black: Color,
    green: Color,
    red: Color,
    greenLight: Color,
    gray: Color,
    orange: Color,
    orangeLight: Color,
    brownLight: Color,
    brown: Color,
    white: Color,
    isLight: Boolean
) {
    var blue by mutableStateOf(blue)
        private set
    var blueLight by mutableStateOf(blueLight)
        private set
    var black by mutableStateOf(black)
        private set
    var green by mutableStateOf(green)
        private set
    var red by mutableStateOf(red)
        private set
    var greenLight by mutableStateOf(greenLight)
        private set
    var gray by mutableStateOf(gray)
        private set
    var orange by mutableStateOf(orange)
        private set
    var orangeLight by mutableStateOf(orangeLight)
        private set
    var brownLight by mutableStateOf(brownLight)
        private set
    var brown by mutableStateOf(brown)
        private set
    var white by mutableStateOf(white)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        blue: Color = this.blue,
        blueLight: Color = this.blueLight,
        black: Color = this.black,
        green: Color = this.green,
        red: Color = this.red,
        greenLight: Color = this.greenLight,
        gray: Color = this.gray,
        orange: Color = this.orange,
        orangeLight: Color = this.orangeLight,
        brownLight: Color = this.brownLight,
        brown: Color = this.brown,
        white: Color = this.white,
        isLight: Boolean = this.isLight
    ): BibleColors = BibleColors(
        blue,
        blueLight,
        black,
        green,
        red,
        greenLight,
        gray,
        orange,
        orangeLight,
        brownLight,
        brown,
        white,
        isLight
    )

    fun updateColorsFrom(other: BibleColors) {
        blue = other.blue
        blueLight = other.blueLight
        black = other.black
        green = other.green
        greenLight = other.greenLight
        red = other.red
        isLight = other.isLight
        gray = other.gray
        orange = other.orange
        orangeLight = other.orangeLight
        brown = other.brown
        white = other.white
        brownLight = other.brownLight
    }
}

private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val Gray = Color(0xFFF5F5F5)

private val Blue = Color(0xE84D94DF)
private val BlueLight = Color(0xFC89C7FF)
private val Brown = Color(0xFFB79F95)
private val BrownLight = Color(0xFFECD8CF)
private val Orange = Color(0xFFFFA000)
private val OrangeLight = Color(0xFFFFD184)
private val Green = Color(0xFF58BE80)
private val GreenLight = Color(0xFFA4ECC1)
private val Red = Color(0xFFF8AFAF)

val randomColors: List<Color> = listOf(
    Blue,
    BlueLight.copy(alpha = 0.5f),
    Brown,
    BrownLight.copy(alpha = 0.5f),
    Orange,
    OrangeLight,
    Green,
    Red.copy(alpha = 0.5f)
)

fun lightColors(
    blue: Color = Blue,
    blueLight: Color = BlueLight,
    black: Color = Black,
    green: Color = Green,
    greenLight: Color = GreenLight,
    red: Color = Red,
    gray: Color = Gray,
    orange: Color = Orange,
    orangeLight: Color = OrangeLight,
    brownLight: Color = BrownLight,
    brown: Color = Brown,
    white: Color = White,
): BibleColors = BibleColors(
    blue = blue,
    blueLight = blueLight,
    black = black,
    green = green,
    greenLight = greenLight,
    red = red,
    gray = gray,
    orange = orange,
    orangeLight = orangeLight,
    brownLight = brownLight,
    brown = brown,
    white = white,
    isLight = true
)

val LocalLightColors = staticCompositionLocalOf { lightColors() }
