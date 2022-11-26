package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.*
import com.ajcm.design.BibleAppState
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.rememberAnimatedBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.design.theme.SetStatusBarColorEffect

@Composable
fun DashboardScreen(appState: BibleAppState = rememberAnimatedBibleAppState()) {
    BibleScreen {
        var showBottomBar by remember { mutableStateOf(false) }
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()
        showBottomBar = currentRoute in bottomNavigationItems.map { it.route.cleanRoute() }

        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = { BottomNavigationBar(appState.navController, showBottomBar) }
        ) { padding ->
            DashboardNavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
            )
        }
        SetStatusBarColorEffect(color = MaterialBibleTheme.colors.green)
    }
}
