package com.ajcm.bible.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
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
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        )
    }

    fun upPress() {
        navController.navigateUp()
    }
}
