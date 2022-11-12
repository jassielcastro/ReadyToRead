package com.ajcm.bible.ui.splash.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.ajcm.bible.R
import com.ajcm.bible.ui.error.ErrorType
import com.ajcm.bible.ui.navigation.StartNavigationActions
import com.ajcm.bible.ui.splash.SplashViewModel
import com.ajcm.design.common.State
import com.ajcm.design.component.MediumSpacer
import com.ajcm.design.component.SmallSpacer
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel,
    actions: StartNavigationActions
) {
    SplashContent()

    val downloadBibles by splashViewModel.downloadBibles.collectAsState()
    when (downloadBibles) {
        is State.Success<*> -> {
            actions.showDashboard()
        }
        State.Empty -> {
            actions.showError("Empty", "Error inesperado", ErrorType.EMPTY)
        }
        is State.Failure -> {
            actions.showError("Error", "Error inesperado", ErrorType.FAILED)
        }
        State.Loading -> {}
    }
}

@Composable
fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialBibleTheme.colors.black,
            style = MaterialBibleTheme.typography.h1
        )

        MediumSpacer()

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .clip(MaterialBibleTheme.shapes.shapeSmall)
                .height(MaterialBibleTheme.dimensions.small),
            backgroundColor = MaterialBibleTheme.colors.greenLight,
            color = MaterialBibleTheme.colors.green
        )

        SmallSpacer()

        Text(
            text = stringResource(id = R.string.load_content),
            color = MaterialBibleTheme.colors.black,
            style = MaterialBibleTheme.typography.caption
        )
    }
}
