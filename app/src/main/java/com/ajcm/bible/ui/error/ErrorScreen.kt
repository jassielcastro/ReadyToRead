package com.ajcm.bible.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.ajcm.bible.ui.components.MediumSpacer
import com.ajcm.bible.ui.theme.MaterialBibleTheme

@Composable
fun ErrorScreen(
    title: String,
    message: String,
    errorIcon: ErrorType
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialBibleTheme.dimensions.normal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = errorIcon.drawable),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(fraction = 0.3f)
                .padding(vertical = MaterialBibleTheme.dimensions.normal)
        )

        Text(
            text = title,
            color = MaterialBibleTheme.colors.black,
            style = MaterialBibleTheme.typography.subtitle,
            textAlign = TextAlign.Center
        )

        MediumSpacer()

        Text(
            text = message,
            color = MaterialBibleTheme.colors.black.copy(alpha = 0.6f),
            style = MaterialBibleTheme.typography.caption
        )
    }
}
