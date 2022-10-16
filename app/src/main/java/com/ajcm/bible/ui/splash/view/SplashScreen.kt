package com.ajcm.bible.ui.splash.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.ajcm.bible.R
import com.ajcm.bible.ui.error.ErrorType
import com.ajcm.bible.ui.navigation.StartNavigationActions
import com.ajcm.bible.ui.splash.viewmodel.SplashViewModel
import com.ajcm.design.common.State
import com.ajcm.design.navigation.collectAsStateLifecycleAware
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel,
    actions: StartNavigationActions
) {
    SplashContent()

    val downloadBibles by splashViewModel.downloadBibles.collectAsStateLifecycleAware(initial = State.Loading)
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
            .background(MaterialBibleTheme.colors.background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialBibleTheme.colors.textPrimary,
            style = MaterialBibleTheme.typography.h1
        )

        Spacer(modifier = Modifier.height(MaterialBibleTheme.dimensions.medium))

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .clip(MaterialBibleTheme.shapes.shapeSmall)
                .height(MaterialBibleTheme.dimensions.small),
            backgroundColor = MaterialBibleTheme.colors.secondary,
            color = MaterialBibleTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(MaterialBibleTheme.dimensions.small))

        Text(
            text = "Cargando contenido...",
            color = MaterialBibleTheme.colors.textPrimary,
            style = MaterialBibleTheme.typography.caption
        )
    }
}
