package com.ajcm.bible.ui.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ajcm.bible.ui.navigation.NavigationItems

fun NavGraphBuilder.splashNav(navController: NavController) {
    composable(NavigationItems.SPLASH_SCREEN) {
        SplashScreen()
    }
}
