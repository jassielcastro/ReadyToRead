package com.ajcm.bible.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ajcm.bible.ui.navigation.Navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kavak.design.BibleAppState
import com.kavak.design.rememberBibleAppState
import com.kavak.design.screen.BibleScreen

@Composable
fun BibleAppUI(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
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
