package com.ajcm.design.theme

import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
object BibleRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = MaterialBibleTheme.colors.green

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = MaterialBibleTheme.colors.green,
        lightTheme = true
    )
}
