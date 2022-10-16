package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialBibleTheme.colors.primary,
        contentColor = MaterialBibleTheme.colors.brownLight,
        modifier = Modifier
            .clip(MaterialBibleTheme.shapes.topShape)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = "") },
                selectedContentColor = MaterialBibleTheme.colors.white,
                unselectedContentColor = MaterialBibleTheme.colors.brownLight,
                alwaysShowLabel = false,
                selected = currentRoute == item.route.cleanRoute(),
                modifier = Modifier
                    .padding(MaterialBibleTheme.dimensions.normal),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
