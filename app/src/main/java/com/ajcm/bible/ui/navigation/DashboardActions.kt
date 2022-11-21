package com.ajcm.bible.ui.navigation

import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.ajcm.bible.ui.MainActivity
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigateTo
import com.ajcm.domain.entity.Bible

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

    fun showReading(bible: Bible) {
        navController.navigateTo(
            route = {
                destination = NavigationItems.Item.READING
                addArgumentValue(bible.id)
                addArgumentValue(bible.nameLocal)
                addArgumentValue(bible.name)
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
