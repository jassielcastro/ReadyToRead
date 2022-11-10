package com.ajcm.bible.ui.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ajcm.bible.ui.navigation.DashboardNavigationHost
import com.ajcm.design.BibleAppState
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.design.theme.SetStatusBarColorEffect

@Composable
fun DashboardScreen(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        var isInSections by remember { mutableStateOf(true) }
        val statusBarColor by animateColorAsState(if (isInSections) MaterialBibleTheme.colors.greenLight else MaterialBibleTheme.colors.white)
        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = { BottomNavigationBar(appState.navController) }
        ) { padding ->
            DashboardNavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
            ) { isInSections = it }
        }
        SetStatusBarColorEffect(color = statusBarColor)
    }
}
