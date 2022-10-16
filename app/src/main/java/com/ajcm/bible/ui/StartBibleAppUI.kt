package com.ajcm.bible.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ajcm.bible.ui.navigation.SetupStartNavigationHost
import com.ajcm.design.BibleAppState
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.SetStatusBarColorEffect

@Composable
fun SetupAppUI(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) { padding ->
            SetupStartNavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
            )
        }
        SetStatusBarColorEffect()
    }
}
