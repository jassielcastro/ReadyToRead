package com.ajcm.bible.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ajcm.bible.ui.error.ErrorType
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.safeNavigateTo

class StartNavigationActions(
    private val navController: NavHostController
) {

    @SuppressLint("ComposableNaming")
    @Composable
    fun showError(title: String, message: String, errorType: ErrorType) {
        navController.safeNavigateTo(
            route = {
                destination = NavigationItems.Item.ERROR
                addArgumentValue(title)
                addArgumentValue(message)
                addArgumentValue(errorType.name)
            },
            navOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        )
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun showDashboard() {
        navController.safeNavigateTo(
            route = {
                destination = NavigationItems.Item.DASHBOARD
            },
            navOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        )
    }
}
