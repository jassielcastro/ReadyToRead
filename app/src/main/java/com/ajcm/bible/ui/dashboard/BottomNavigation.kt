package com.ajcm.bible.ui.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.common.drawRectBehind
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun BottomNavigationBar(navController: NavController, showBottomBar: Boolean) {
    AnimatedVisibility(
        showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(
            backgroundColor = MaterialBibleTheme.colors.white
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()

            bottomNavigationItems.forEach { item ->
                val isSelected = currentRoute == item.route.cleanRoute()
                val heightMultiplier: Float by animateFloatAsState(if (isSelected) 0.65f else 0f)
                val alpha: Float by animateFloatAsState(if (isSelected) 0.7f else 0f)
                val shapeColor = MaterialBibleTheme.colors.green.copy(alpha = alpha)
                val icon = if (isSelected) item.iconSelected else item.iconNormal

                BottomNavigationItem(
                    icon = { Icon(painterResource(id = icon), contentDescription = "") },
                    selectedContentColor = MaterialBibleTheme.colors.white,
                    unselectedContentColor = MaterialBibleTheme.colors.black.copy(alpha = 0.35f),
                    alwaysShowLabel = false,
                    selected = isSelected,
                    modifier = Modifier
                        .drawRectBehind(
                            backgroundColor = shapeColor,
                            cornerRadius = 40.dp,
                            withMultiplier = 2f,
                            heightMultiplier = heightMultiplier
                        ),
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
