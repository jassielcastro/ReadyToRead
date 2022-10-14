package com.ajcm.bible.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ajcm.bible.ui.error.ErrorType
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.safeNavigateTo

class NavigationActions(
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
            }
        )
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun showDashboard() {
        navController.safeNavigateTo(
            route = {
                destination = NavigationItems.Item.DASHBOARD
            }
        )
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

}
