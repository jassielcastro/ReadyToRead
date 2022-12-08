package com.ajcm.bible.ui.navigation

import androidx.annotation.DrawableRes
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.navigation.NavigationItems
import com.ajcm.bible.ui.components.navigation.navigationRoute
import com.ajcm.bible.ui.dashboard.search.SEARCH_WITH_ARG_KEY

val sectionsDestination = navigationRoute {
    destination = NavigationItems.Item.SECTIONS
}

// Route destination
val searchDestination = navigationRoute {
    destination = NavigationItems.Item.SEARCH
    addArgumentParm(SEARCH_WITH_ARG_KEY)
}

val searchDestinationArgs = navigationRoute {
    destination = NavigationItems.Item.SEARCH
    addArgumentValue(DashboardActions.NONE)
}

val favoritesDestination = navigationRoute {
    destination = NavigationItems.Item.FAVORITES
}

val moreMenuDestination = navigationRoute {
    destination = NavigationItems.Item.MORE_MENU
}

data class BottomItem(
    val route: String,
    @DrawableRes val iconNormal: Int,
    @DrawableRes val iconSelected: Int
)

val bottomNavigationItems = listOf(
    BottomItem(sectionsDestination, R.drawable.ic_books_normal, R.drawable.ic_books_selected),
    BottomItem(searchDestinationArgs, R.drawable.ic_search_normal, R.drawable.ic_search_selected),
    BottomItem(favoritesDestination, R.drawable.ic_bookmarks_normal, R.drawable.ic_bookmarks_selected),
    BottomItem(moreMenuDestination, R.drawable.ic_more_normal, R.drawable.ic_more_filled)
)
