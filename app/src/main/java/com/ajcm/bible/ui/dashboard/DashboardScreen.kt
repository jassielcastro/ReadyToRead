package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ajcm.bible.ui.navigation.DashboardNavigationHost
import com.ajcm.design.BibleAppState
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.SetStatusBarColorEffect

@Composable
fun DashboardScreen(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = { BottomNavigationBar(appState.navController) }
        ) { padding ->
            DashboardNavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
            )
        }
        SetStatusBarColorEffect()
    }
}
