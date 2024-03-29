package com.ajcm.bible.ui.dashboard.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ajcm.bible.R
import com.ajcm.bible.ui.BibleScreen
import com.ajcm.bible.ui.components.LargeSpacer
import com.ajcm.bible.ui.components.NormalSpacer
import com.ajcm.bible.ui.components.SmallSpacer
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.bible.ui.theme.MaterialBibleTheme

@Composable
fun TextResizeScreen(
    configurationsViewModel: ConfigurationsViewModel
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val configurations by configurationsViewModel.configurations.collectAsState()

        LaunchedEffect(Unit) {
            configurationsViewModel.getConfigurations()
        }

        TextResizeScreen(
            textSizeMultiplier = configurations?.textSizeMultiplier?.toFloat() ?: 1f,
            onSliderFinished = {
                configurationsViewModel.updateTextSizeMultiplier(it)
            }
        )
    }
}

@Composable
fun TextResizeScreen(
    textSizeMultiplier: Float,
    onSliderFinished: (Int) -> Unit
) {

    var internalMultiplier by remember { mutableStateOf(textSizeMultiplier) }

    LaunchedEffect(textSizeMultiplier) {
        internalMultiplier = textSizeMultiplier
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "Tamaño de fuente",
            textAlign = TextAlign.Center,
            style = MaterialBibleTheme.typography.section,
            color = MaterialBibleTheme.colors.black,
            modifier = Modifier
                .padding(MaterialBibleTheme.dimensions.normal)
                .fillMaxWidth()
        )

        NormalSpacer()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_format_size),
                contentDescription = "",
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.normal)
                    .weight(0.2f)
                    .bounceClick(
                        onClicked = {
                            if (internalMultiplier > 1f) {
                                onSliderFinished((internalMultiplier - 1).toInt())
                            }
                        }
                    )
            )

            Slider(
                value = internalMultiplier,
                onValueChange = {
                    internalMultiplier = it
                },
                valueRange = 1f..5f,
                steps = 3,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialBibleTheme.colors.green,
                    activeTrackColor = MaterialBibleTheme.colors.greenLight,
                    activeTickColor = MaterialBibleTheme.colors.greenLight,
                ),
                onValueChangeFinished = {
                    onSliderFinished(internalMultiplier.toInt())
                },
                modifier = Modifier
                    .weight(0.6f)
            )

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_format_size),
                contentDescription = "",
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xlarge)
                    .weight(0.2f)
                    .bounceClick(
                        onClicked = {
                            if (internalMultiplier < 5f) {
                                onSliderFinished((internalMultiplier + 1).toInt())
                            }
                        }
                    )
            )
        }

        LargeSpacer()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {
        TextResizeScreen(
            textSizeMultiplier = 2f,
            onSliderFinished = {}
        )
    }
}
