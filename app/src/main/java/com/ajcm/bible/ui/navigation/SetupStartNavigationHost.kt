package com.ajcm.bible.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajcm.bible.ui.dashboard.DashboardScreen
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.splash.SplashScreen
import com.ajcm.bible.ui.splash.SplashViewModel

@Composable
fun SetupStartNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val actions = remember(navController) { StartNavigationActions(navController) }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = splashDestination
    ) {
        composable(splashDestination) { backStack ->
            val splashViewModel = hiltViewModel<SplashViewModel>(backStack)
            SplashScreen(splashViewModel, actions)
        }
        composable(dashboardDestination) {
            DashboardScreen()
        }
        composable(
            route = errorscreenDestination,
            arguments = allowedErrorArguments
        ) { backStack ->
            ErrorScreen(backStack.arguments)
        }
    }
}
