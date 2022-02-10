package com.ajcm.bible.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ajcm.bible.ui.splash.splashNav

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItems.SPLASH_SCREEN
    ) {
        splashNav(navController)
    }
}
