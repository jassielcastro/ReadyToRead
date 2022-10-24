package com.ajcm.bible.ui.navigation

import androidx.annotation.DrawableRes
import com.ajcm.bible.R
import com.ajcm.bible.ui.dashboard.search.SEARCH_WITH_ARG_KEY
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigationRoute

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

data class BottomItem(val route: String, @DrawableRes val icon: Int)

val bottomNavigationItems = listOf(
    BottomItem(sectionsDestination, R.drawable.ic_home),
    BottomItem(searchDestinationArgs, R.drawable.ic_search),
    BottomItem(favoritesDestination, R.drawable.ic_bookmarks)
)
