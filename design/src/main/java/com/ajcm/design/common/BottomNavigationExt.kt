package com.ajcm.design.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.drawRectBehind(
    backgroundColor: Color,
    cornerRadius: Dp
) = drawBehind {
    drawRoundRect(
        topLeft = Offset(size.width * 0.255f, size.height * 0.2f),
        color = backgroundColor,
        size = Size(width = size.width / 2, height = size.height * 0.65f),
        cornerRadius = CornerRadius(x = cornerRadius.toPx(), cornerRadius.toPx())
    )
}
