package com.ajcm.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object MaterialBibleTheme {
    val colors: BibleColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLightColors.current
    val typography: BibleTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val shapes: BibleShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
    val dimensions: BibleDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

@Composable
fun BibleComposeTheme(
    colors: BibleColors = MaterialBibleTheme.colors,
    typography: BibleTypography = MaterialBibleTheme.typography,
    dimensions: BibleDimensions = MaterialBibleTheme.dimensions,
    shapes: BibleShape = MaterialBibleTheme.shapes,
    content: @Composable () -> Unit
) {

    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalLightColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        content()
    }
}
