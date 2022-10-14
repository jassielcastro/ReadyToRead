package com.ajcm.design.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun Circle(
    color: Color
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        drawCircle(
            color = color,
            center = Offset(x = 0f, y = size.height),
            radius = (size.width - (size.width / 3))
        )
    }
}
