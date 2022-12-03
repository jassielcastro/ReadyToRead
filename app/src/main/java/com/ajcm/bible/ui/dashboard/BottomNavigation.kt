package com.ajcm.bible.ui.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun BottomNavigationBar(navController: NavController, showBottomBar: Boolean) {
    AnimatedVisibility(
        showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = 150,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        BottomNavigation(
            backgroundColor = MaterialBibleTheme.colors.white,
            elevation = MaterialBibleTheme.dimensions.elevationSmall
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()
            val unselectedColor = MaterialBibleTheme.colors.black.copy(alpha = 0.35f)

            bottomNavigationItems.forEach { item ->
                val isSelected = currentRoute == item.route.cleanRoute()
                val backgroundColor by animateColorAsState(if (isSelected) MaterialBibleTheme.colors.green else MaterialBibleTheme.colors.white)
                val icon = if (isSelected) item.iconSelected else item.iconNormal

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = icon),
                            contentDescription = ""
                        )
                    },
                    selectedContentColor = MaterialBibleTheme.colors.white,
                    unselectedContentColor = unselectedColor,
                    alwaysShowLabel = false,
                    selected = isSelected,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialBibleTheme.dimensions.normal,
                            vertical = MaterialBibleTheme.dimensions.medium
                        )
                        .background(backgroundColor, MaterialBibleTheme.shapes.shapeXLarge),
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
}
