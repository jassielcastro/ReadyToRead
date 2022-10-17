package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialBibleTheme.colors.white
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?.cleanRoute()

        bottomNavigationItems.forEach { item ->
            val isSelected = currentRoute == item.route.cleanRoute()
            val shapeColor = if (isSelected) {
                MaterialBibleTheme.colors.green.copy(alpha = 0.7f)
            } else {
                Color.Transparent
            }

            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = "") },
                selectedContentColor = MaterialBibleTheme.colors.white,
                unselectedContentColor = MaterialBibleTheme.colors.black.copy(alpha = 0.5f),
                alwaysShowLabel = false,
                selected = currentRoute == item.route.cleanRoute(),
                modifier = Modifier
                    .padding(MaterialBibleTheme.dimensions.medium)
                    .drawBehind {
                        drawRoundRect(
                            topLeft = Offset(size.width * 0.25f, 0f),
                            color = shapeColor,
                            size = Size(width = size.width / 2, height = size.height),
                            cornerRadius = CornerRadius(x = 36.dp.toPx(), 36.dp.toPx())
                        )
                    },
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
