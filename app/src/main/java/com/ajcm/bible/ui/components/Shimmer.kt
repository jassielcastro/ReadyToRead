package com.ajcm.bible.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import com.ajcm.bible.ui.theme.MaterialBibleTheme

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

            NormalSpacer()

            repeat(8) {
                Spacer(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialBibleTheme.dimensions.normal
                        )
                        .fillMaxWidth()
                        .height(MaterialBibleTheme.dimensions.cardInfo)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )

                NormalSpacer()
            }
        }
    }
}

@Composable
fun LoadBibleDetailShimmer() {
    ShimmerEfect { brush ->
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(MaterialBibleTheme.dimensions.normal)
            ) {

                Spacer(
                    modifier = Modifier
                        .size(MaterialBibleTheme.dimensions.cardInfo)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )

                SmallSpacer()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = MaterialBibleTheme.dimensions.medium)
                            .fillMaxWidth()
                            .height(MaterialBibleTheme.dimensions.large)
                            .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                    )

                    MediumSpacer()

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = MaterialBibleTheme.dimensions.medium)
                            .fillMaxWidth()
                            .height(MaterialBibleTheme.dimensions.large)
                            .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                    )

                }
            }

            SmallSpacer()

            Spacer(
                modifier = Modifier
                    .padding(horizontal = MaterialBibleTheme.dimensions.normal)
                    .fillMaxWidth()
                    .height(MaterialBibleTheme.dimensions.large)
                    .background(brush, MaterialBibleTheme.shapes.shapeMedium)
            )

            MediumSpacer()

            Spacer(
                modifier = Modifier
                    .padding(horizontal = MaterialBibleTheme.dimensions.normal)
                    .fillMaxWidth()
                    .height(MaterialBibleTheme.dimensions.large)
                    .background(brush, MaterialBibleTheme.shapes.shapeMedium)
            )

            NormalSpacer()
        }
    }
}

@Composable
fun LoadBooksShimmer() {
    ShimmerEfect { brush ->
        Column(
            modifier = Modifier
                .padding(top = MaterialBibleTheme.dimensions.xsmall)
                .fillMaxSize()
                .background(MaterialBibleTheme.colors.white)
                .padding(MaterialBibleTheme.dimensions.medium)
        ) {
            MediumSpacer()

            repeat(6) {
                Spacer(
                    modifier = Modifier
                        .padding(bottom = MaterialBibleTheme.dimensions.normal)
                        .fillMaxWidth(0.5f)
                        .height(MaterialBibleTheme.dimensions.xlarge)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )

                Spacer(
                    modifier = Modifier
                        .padding(bottom = MaterialBibleTheme.dimensions.normal)
                        .fillMaxWidth(0.3f)
                        .height(MaterialBibleTheme.dimensions.xlarge)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )

                Spacer(
                    modifier = Modifier
                        .padding(bottom = MaterialBibleTheme.dimensions.normal)
                        .fillMaxWidth(0.7f)
                        .height(MaterialBibleTheme.dimensions.xlarge)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )
            }

            MediumSpacer()
        }
    }
}

@Composable
fun LoadChapterShimmer() {
    ShimmerEfect { brush ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = MaterialBibleTheme.dimensions.xsmall)
                .fillMaxSize()
                .background(MaterialBibleTheme.colors.white)
                .padding(MaterialBibleTheme.dimensions.normal)
        ) {
            items(17) {
                Spacer(
                    modifier = Modifier
                        .padding(
                            end = MaterialBibleTheme.dimensions.normal,
                            bottom = MaterialBibleTheme.dimensions.normal
                        )
                        .size(MaterialBibleTheme.dimensions.xxxlarge)
                        .background(brush, MaterialBibleTheme.shapes.shapeMedium)
                )
            }
        }
    }
}
