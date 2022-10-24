package com.ajcm.bible.ui.navigation

import androidx.navigation.NavHostController
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
            }
        )
    }
}
