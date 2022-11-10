package com.ajcm.design.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class BibleShape(
    val shapeSmall: Shape = RoundedCornerShape(4.dp),
    val shapeMedium: Shape = RoundedCornerShape(8.dp),
    val shapeNormal: Shape = RoundedCornerShape(16.dp),
    val shapeLarge: Shape = RoundedCornerShape(24.dp),
    val shapeXLarge: Shape = RoundedCornerShape(32.dp),
    val startShape: Shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
    val endShape: Shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
    val topShape: Shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    val bottomShape: Shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
)

val LocalShapes = staticCompositionLocalOf { BibleShape() }
