package com.ajcm.bible.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ajcm.bible.ui.navigation.Navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ajcm.design.BibleAppState
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen

@Composable
fun BibleAppUI(viewModels: List<ViewModel>, appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) { padding ->
            Navigation(
                navController = appState.navController,
                modifier = Modifier.padding(padding),
                viewModels = viewModels
            )
        }
        SetStatusBarColorEffect()
    }
}

@Composable
private fun SetStatusBarColorEffect(
    color: Color = MaterialTheme.colors.primaryVariant,
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    SideEffect {
        systemUiController.setStatusBarColor(color)
    }
}
