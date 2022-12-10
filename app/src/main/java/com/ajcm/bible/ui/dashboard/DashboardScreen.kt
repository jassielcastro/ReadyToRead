package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.BibleAppState
import com.ajcm.bible.ui.BibleScreen
import com.ajcm.bible.ui.components.common.cleanRoute
import com.ajcm.bible.ui.navigation.DashboardNavigationHost
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.bible.ui.rememberAnimatedBibleAppState
import com.ajcm.bible.ui.theme.MaterialBibleTheme
import com.ajcm.bible.ui.theme.SetStatusBarColorEffect

@Composable
fun DashboardScreen(appState: BibleAppState = rememberAnimatedBibleAppState()) {
    BibleScreen {
        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val showBottomBar by remember {
            derivedStateOf {
                val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()
                currentRoute in bottomNavigationItems.map { it.route.cleanRoute() }
            }
        }

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
