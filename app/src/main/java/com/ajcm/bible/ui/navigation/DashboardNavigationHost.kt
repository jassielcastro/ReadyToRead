package com.ajcm.bible.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ajcm.bible.ui.components.common.cleanRoute
import com.ajcm.bible.ui.dashboard.favorite.FavoriteScreen
import com.ajcm.bible.ui.dashboard.more.MoreMenuScreen
import com.ajcm.bible.ui.dashboard.search.SearchScreen
import com.ajcm.bible.ui.dashboard.search.allowedSearchArguments
import com.ajcm.bible.ui.dashboard.sections.SectionsScreen
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.reading.ReadingScreen
import com.ajcm.bible.ui.reading.allowedReadingBibleArguments
import com.ajcm.bible.ui.reading.readingDestination
import com.ajcm.bible.ui.reading.viewmodel.ReadingViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DashboardNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val actions = remember(navController) { DashboardActions(navController) }
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = sectionsDestination
    ) {
        composable(
            route = sectionsDestination,
            enterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerLeft()
                )
            },
            exitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerLeft()
                )
            },
            popEnterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerRigth()
                )
            },
            popExitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerRight()
                )
            }
        ) {
            val viewModel = hiltViewModel<SharedBibleViewModel>(it)
            SectionsScreen(actions, viewModel)
        }
        composable(
            route = searchDestination,
            arguments = allowedSearchArguments,
            enterTransition = {
                animateEnterDestination(
                    sectionAnimation = slideIntoContainerLeft(),
                    favoriteAnimation = slideIntoContainerRigth(),
                    moreMenuAnimation = slideIntoContainerRigth()
                )
            },
            exitTransition = {
                animateExitDestination(
                    sectionAnimation = slideOutOfContainerLeft(),
                    favoriteAnimation = slideOutOfContainerRight(),
                    moreMenuAnimation = slideOutOfContainerRight()
                )
            },
            popEnterTransition = {
                animateEnterDestination(
                    sectionAnimation = slideIntoContainerRigth(),
                    favoriteAnimation = slideIntoContainerLeft(),
                    moreMenuAnimation = slideIntoContainerLeft()
                )
            },
            popExitTransition = {
                animateExitDestination(
                    sectionAnimation = slideOutOfContainerRight(),
                    favoriteAnimation = slideOutOfContainerLeft(),
                    moreMenuAnimation = slideOutOfContainerLeft()
                )
            }
        ) {
            val viewModel = hiltViewModel<SharedBibleViewModel>(it)
            SearchScreen(viewModel, it.arguments, actions)
        }
        composable(
            route = favoritesDestination,
            enterTransition = {
                animateEnterDestination(
                    sectionAnimation = slideIntoContainerLeft(),
                    searchAnimation = slideIntoContainerLeft(),
                    moreMenuAnimation = slideIntoContainerRigth()
                )
            },
            exitTransition = {
                animateExitDestination(
                    sectionAnimation = slideOutOfContainerLeft(),
                    searchAnimation = slideOutOfContainerLeft(),
                    moreMenuAnimation = slideOutOfContainerRight()
                )
            },
            popEnterTransition = {
                animateEnterDestination(
                    sectionAnimation = slideIntoContainerRigth(),
                    searchAnimation = slideIntoContainerRigth(),
                    moreMenuAnimation = slideIntoContainerLeft()
                )
            },
            popExitTransition = {
                animateExitDestination(
                    sectionAnimation = slideOutOfContainerRight(),
                    searchAnimation = slideOutOfContainerRight(),
                    moreMenuAnimation = slideOutOfContainerLeft()
                )
            }
        ) {
            val viewModel = hiltViewModel<SharedBibleViewModel>(it)
            FavoriteScreen(viewModel, actions)
        }
        composable(
            route = moreMenuDestination,
            enterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerLeft()
                )
            },
            exitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerRight()
                )
            },
            popEnterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerRigth()
                )
            },
            popExitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerRight()
                )
            }
        ) {
            val viewModel = hiltViewModel<ConfigurationsViewModel>(it)
            MoreMenuScreen(viewModel)
        }
        composable(
            route = readingDestination,
            arguments = allowedReadingBibleArguments,
            enterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerLeft()
                )
            },
            exitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerRight()
                )
            },
            popEnterTransition = {
                animateEnterDestination(
                    all = slideIntoContainerRigth()
                )
            },
            popExitTransition = {
                animateExitDestination(
                    all = slideOutOfContainerRight()
                )
            }
        ) {
            val configurationViewModel = hiltViewModel<ConfigurationsViewModel>(it)
            val readingViewModel = hiltViewModel<ReadingViewModel>(it)
            ReadingScreen(readingViewModel, configurationViewModel, it.arguments, actions)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.slideIntoContainerLeft() =
    slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400)) + fadeIn()

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.slideOutOfContainerLeft() =
    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400)) + fadeOut()

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.slideIntoContainerRigth() =
    slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(400)) + fadeIn()

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.slideOutOfContainerRight() =
    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(400)) + fadeOut()

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.animateEnterDestination(
    all: EnterTransition? = null,
    sectionAnimation: EnterTransition? = all,
    searchAnimation: EnterTransition? = all,
    favoriteAnimation: EnterTransition? = all,
    moreMenuAnimation: EnterTransition? = all,
) = when (this.initialState.destination.route?.cleanRoute()) {
    sectionsDestination.cleanRoute() -> sectionAnimation
    searchDestination.cleanRoute() -> searchAnimation
    favoritesDestination.cleanRoute() -> favoriteAnimation
    moreMenuDestination.cleanRoute() -> moreMenuAnimation
    else -> null
}

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<NavBackStackEntry>.animateExitDestination(
    all: ExitTransition? = null,
    sectionAnimation: ExitTransition? = all,
    searchAnimation: ExitTransition? = all,
    favoriteAnimation: ExitTransition? = all,
    moreMenuAnimation: ExitTransition? = all,
) = when (this.initialState.destination.route?.cleanRoute()) {
    sectionsDestination.cleanRoute() -> sectionAnimation
    searchDestination.cleanRoute() -> searchAnimation
    favoritesDestination.cleanRoute() -> favoriteAnimation
    moreMenuDestination.cleanRoute() -> moreMenuAnimation
    else -> null
}
