package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.DashboardNavigationHost
import com.ajcm.bible.ui.navigation.favoritesDestination
import com.ajcm.bible.ui.navigation.searchDestination
import com.ajcm.bible.ui.navigation.sectionsDestination
import com.ajcm.design.BibleAppState
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.rememberBibleAppState
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.SetStatusBarColorEffect

@Composable
fun DashboardScreen(appState: BibleAppState = rememberBibleAppState()) {
    BibleScreen {
        var bottomBarState by remember { (mutableStateOf(true)) }

        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()
        bottomBarState = when (currentRoute) {
            sectionsDestination.cleanRoute() -> true
            searchDestination.cleanRoute() -> true
            favoritesDestination.cleanRoute() -> true
            else -> false
        }

        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = { BottomNavigationBar(appState.navController, bottomBarState) }
        ) { padding ->
            DashboardNavigationHost(
                navController = appState.navController,
                modifier = Modifier.padding(padding)
            )
        }
        SetStatusBarColorEffect()
    }
}
