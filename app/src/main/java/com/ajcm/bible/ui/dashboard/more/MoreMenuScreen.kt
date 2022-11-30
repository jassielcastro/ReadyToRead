package com.ajcm.bible.ui.dashboard.more

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.AnimatedSurface
import com.ajcm.bible.ui.dashboard.configuration.TextResizeScreen
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.BottomSheetContainer
import com.ajcm.design.component.LargeSpacer
import com.ajcm.design.component.NormalSpacer
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun MoreMenuScreen(
    configurationsViewModel: ConfigurationsViewModel
) {
    BottomSheetContainer(
        sheetContent = {
           TextResizeScreen(configurationsViewModel)
        },
        content = { showSheet ->
            MoreMenuScreen(showSheet)
        }
    )
}

@Composable
fun MoreMenuScreen(showBibleSheet: (Bundle) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialBibleTheme.colors.green)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialBibleTheme.typography.h1,
            color = MaterialBibleTheme.colors.white,
            modifier = Modifier
                .padding(MaterialBibleTheme.dimensions.normal)
                .fillMaxWidth()
        )


        Surface(
            color = MaterialBibleTheme.colors.white,
            shape = MaterialBibleTheme.shapes.topShape,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                LargeSpacer()

                Text(
                    text = "Configuración",
                    textAlign = TextAlign.Start,
                    style = MaterialBibleTheme.typography.section,
                    color = MaterialBibleTheme.colors.black,
                    modifier = Modifier
                        .padding(start = MaterialBibleTheme.dimensions.normal)
                )

                ChangeAppFontSize(showBibleSheet)

                NormalSpacer()

                Text(
                    text = "Conoce más",
                    textAlign = TextAlign.Start,
                    style = MaterialBibleTheme.typography.section,
                    color = MaterialBibleTheme.colors.black,
                    modifier = Modifier
                        .padding(start = MaterialBibleTheme.dimensions.normal)
                )

                ApiReference()

            }

        }
    }
}

@Composable
fun ChangeAppFontSize(
    showBibleSheet: (Bundle) -> Unit = {}
) {
    Surface(
        color = MaterialBibleTheme.colors.gray,
        shape = MaterialBibleTheme.shapes.shapeMedium,
        modifier = Modifier
            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
            .fillMaxWidth()
            .bounceClick(
                onCLicked = {
                    showBibleSheet(bundleOf())
                }
            )
            .clip(MaterialBibleTheme.shapes.shapeMedium)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Cambiar tamaño de fuente",
                textAlign = TextAlign.Start,
                style = MaterialBibleTheme.typography.caption,
                color = MaterialBibleTheme.colors.black,
                modifier = Modifier
                    .padding(MaterialBibleTheme.dimensions.normal)
                    .weight(0.8f)
            )

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_format_size),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.2f)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ApiReference() {
    AnimatedSurface(
        collapsedContent = { CollapsedInfo() },
        expandedContent = { ExpandedInfo() }
    )
}

@Composable
private fun CollapsedInfo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "API.Bible ¿Qué es?",
            textAlign = TextAlign.Start,
            style = MaterialBibleTheme.typography.caption,
            color = MaterialBibleTheme.colors.black,
            modifier = Modifier
                .padding(MaterialBibleTheme.dimensions.normal)
                .weight(0.8f)
        )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_read_more),
            contentDescription = "",
            modifier = Modifier
                .weight(0.2f)
        )
    }
}

@Composable
private fun ExpandedInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        NormalSpacer()

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_api_logo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.7f)
        )

        Text(
            text = stringResource(id = R.string.api_bible_description),
            textAlign = TextAlign.Justify,
            style = MaterialBibleTheme.typography.caption,
            color = MaterialBibleTheme.colors.black,
            modifier = Modifier
                .padding(MaterialBibleTheme.dimensions.normal)
        )

        NormalSpacer()

        Text(
            text = stringResource(id = R.string.api_bible_copy),
            textAlign = TextAlign.Justify,
            style = MaterialBibleTheme.typography.subCaption,
            color = MaterialBibleTheme.colors.black,
            modifier = Modifier
                .padding(MaterialBibleTheme.dimensions.normal)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {
        MoreMenuScreen()
    }
}
