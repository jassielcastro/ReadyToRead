package com.ajcm.bible.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajcm.splash.ui.SplashScreen
import com.kavak.design.NavigationItems

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItems.SPLASH_SCREEN
    ) {
        composable(NavigationItems.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
    }
}
