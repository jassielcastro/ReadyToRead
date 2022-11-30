package com.ajcm.bible.ui.dashboard.configuration

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.NormalSpacer
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.MaterialBibleTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TextResizeScreen(
    configurationsViewModel: ConfigurationsViewModel
) {

    var onChange by remember { mutableStateOf(false) }

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

            var sliderPosition by remember { mutableStateOf(0f) }

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_format_size),
                contentDescription = "",
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.normal)
                    .weight(0.2f)
                    .bounceClick(
                        onCLicked = {
                            if (sliderPosition > 0f) {
                                sliderPosition--
                            }
                        }
                    )
            )

            Slider(
                value = sliderPosition,
                onValueChange = {
                    onChange = true
                    sliderPosition = it
                },
                valueRange = 0f..5f,
                steps = 4,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialBibleTheme.colors.green,
                    activeTrackColor = MaterialBibleTheme.colors.greenLight,
                    activeTickColor = MaterialBibleTheme.colors.greenLight,
                ),
                onValueChangeFinished = {
                    onChange = false
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
                        onCLicked = {
                            if (sliderPosition < 5f) {
                                sliderPosition++
                            }
                        }
                    )
            )
        }

        AnimatedContent(onChange) { state ->
            Text(
                text = if (state) "Guardando..." else "Listo!",
                textAlign = TextAlign.Center,
                style = MaterialBibleTheme.typography.subCaption2,
                color = MaterialBibleTheme.colors.black,
                modifier = Modifier
                    .padding(MaterialBibleTheme.dimensions.normal)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {
        TextResizeScreen(ConfigurationsViewModel())
    }
}
