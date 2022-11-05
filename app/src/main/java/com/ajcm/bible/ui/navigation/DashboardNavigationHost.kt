package com.ajcm.bible.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajcm.bible.ui.dashboard.favorite.FavoriteScreen
import com.ajcm.bible.ui.dashboard.favorite.FavoriteViewModel
import com.ajcm.bible.ui.dashboard.search.SearchScreen
import com.ajcm.bible.ui.dashboard.search.SearchViewModel
import com.ajcm.bible.ui.dashboard.search.allowedSearchArguments
import com.ajcm.bible.ui.dashboard.sections.SectionViewModel
import com.ajcm.bible.ui.dashboard.sections.SectionsScreen

@Composable
fun DashboardNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val actions = remember(navController) { DashboardActions(navController) }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = sectionsDestination
    ) {
        composable(sectionsDestination) {
            val viewModel = hiltViewModel<SectionViewModel>(it)
            SectionsScreen(actions, viewModel)
        }
        composable(
            route = searchDestination,
            arguments = allowedSearchArguments
        ) {
            val viewModel = hiltViewModel<SearchViewModel>(it)
            SearchScreen(viewModel, it.arguments, actions)
        }
        composable(favoritesDestination) {
            val viewModel = hiltViewModel<FavoriteViewModel>(it)
            FavoriteScreen(viewModel)
        }
    }
}
