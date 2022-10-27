package com.ajcm.design.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
private fun ShimmerEfect(body: @Composable (Brush) -> Unit) {
    val gradient = listOf(
        MaterialBibleTheme.colors.gray,
        MaterialBibleTheme.colors.gray.copy(alpha = 0.6f),
        MaterialBibleTheme.colors.gray
    )

    val transition = rememberInfiniteTransition()

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )

    val brush = linearGradient(
        colors = gradient,
        start = Offset(100f, 100f),
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )

    body(brush)
}

@Composable
fun LoadLanguageShimmer(modifier: Modifier) {
    ShimmerEfect { brush ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier)
        ) {
            repeat(8) {
                Spacer(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialBibleTheme.dimensions.normal,
                            vertical = MaterialBibleTheme.dimensions.small
                        )
                        .width(MaterialBibleTheme.dimensions.cardInfo)
                        .height(MaterialBibleTheme.dimensions.normal)
                        .clip(MaterialBibleTheme.shapes.shapeSmall)
                        .background(brush)
                )
            }
        }
    }
}

@Composable
fun LoadBiblesShimmer(modifier: Modifier) {
    ShimmerEfect { brush ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier)
        ) {

            Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.normal))

            repeat(8) {
                Spacer(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialBibleTheme.dimensions.normal
                        )
                        .fillMaxWidth()
                        .height(MaterialBibleTheme.dimensions.cardInfo)
                        .clip(MaterialBibleTheme.shapes.shapeMedium)
                        .background(brush)
                )

                Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.normal))
            }
        }
    }
}
