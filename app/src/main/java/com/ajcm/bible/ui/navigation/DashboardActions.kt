package com.ajcm.bible.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ajcm.bible.ui.MainActivity
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigateTo

class DashboardActions(
    private val navController: NavHostController
) {

    companion object {
        const val NONE = "NONE"
    }

    fun showSearchBy(value: String = NONE) {
        navController.navigateTo(
            route = {
                destination = NavigationItems.Item.SEARCH
                addArgumentValue(value)
            },
            navOptions = navOptions {
                navController.graph.startDestinationRoute?.let { screen_route ->
                    popUpTo(screen_route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        )
    }

    fun showReading() {
        navController.navigateTo(
            route = {
                destination = NavigationItems.Item.READING
            }
        )
    }

    fun upPress() {
        navController.navigateUp()
    }

    fun onBack() {
        (navController.context as MainActivity).onBackPressed()
    }
}
