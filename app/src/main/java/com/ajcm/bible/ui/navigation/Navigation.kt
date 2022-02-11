package com.ajcm.bible.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajcm.splash.ui.SplashScreen
import com.ajcm.design.NavigationItems
import com.ajcm.splash.viewModel.SplashViewModel

@Composable
fun Navigation(
    viewModels: List<ViewModel>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItems.SPLASH_SCREEN
    ) {
        composable(NavigationItems.SPLASH_SCREEN) {
            val splashViewModel = viewModels
                .filterIsInstance<SplashViewModel>()
                .first()
            SplashScreen(navController, splashViewModel)
        }
    }
}
