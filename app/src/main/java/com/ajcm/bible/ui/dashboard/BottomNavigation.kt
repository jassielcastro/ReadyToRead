package com.ajcm.bible.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajcm.bible.ui.navigation.bottomNavigationItems
import com.ajcm.design.common.cleanRoute
import com.ajcm.design.component.Circle
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
            val circleColor = if (isSelected) {
                MaterialBibleTheme.colors.green.copy(alpha = 0.1f)
            } else {
                Color.Transparent
            }
            ConstraintLayout(
                modifier = Modifier
                    .weight(1f)
            ) {
                val content = createRef()

                Canvas(
                    modifier = Modifier
                        .padding(MaterialBibleTheme.dimensions.medium)
                        .fillMaxSize()
                        .constrainAs(content) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                ) {
                    drawCircle(color = circleColor)
                }
                Row {
                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = item.icon), contentDescription = "") },
                        selectedContentColor = MaterialBibleTheme.colors.green,
                        unselectedContentColor = MaterialBibleTheme.colors.black.copy(alpha = 0.5f),
                        alwaysShowLabel = false,
                        selected = currentRoute == item.route.cleanRoute(),
                        modifier = Modifier
                            .padding(MaterialBibleTheme.dimensions.medium),
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
}
