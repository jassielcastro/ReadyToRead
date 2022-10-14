package com.ajcm.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BibleDimensions(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val normal: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xlarge: Dp = 32.dp,
    val xxlarge: Dp = 42.dp,
    val xxxlarge: Dp = 58.dp,
    val zero: Dp = 0.dp,
    val elevationSmall: Dp = 4.dp,
    val elevationNormal: Dp = 8.dp,
    val bookInfoWidth: Dp = 160.dp,
    val search: Dp = 92.dp,
    val cardInfo: Dp = 94.dp,
)

val LocalDimensions = staticCompositionLocalOf { BibleDimensions() }
