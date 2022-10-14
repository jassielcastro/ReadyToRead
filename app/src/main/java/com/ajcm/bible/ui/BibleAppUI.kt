package com.ajcm.bible.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ajcm.bible.ui.navigation.NavigationHost
import com.ajcm.design.BibleAppState
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BibleAppUI(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) { padding ->
            NavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
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
