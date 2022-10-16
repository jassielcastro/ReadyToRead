package com.ajcm.bible.ui.navigation

import com.ajcm.bible.ui.error.ERROR_MESSAGE_ARG_KEY
import com.ajcm.bible.ui.error.ERROR_TITLE_ARG_KEY
import com.ajcm.bible.ui.error.ERROR_TYPE_ARG_KEY
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigationRoute

val splashDestination = navigationRoute {
    destination = NavigationItems.Item.SPLASH
}

val dashboardDestination = navigationRoute {
    destination = NavigationItems.Item.DASHBOARD
}

val errorscreenDestination = navigationRoute {
    destination = NavigationItems.Item.ERROR
    addArgumentParm(ERROR_TITLE_ARG_KEY)
    addArgumentParm(ERROR_MESSAGE_ARG_KEY)
    addArgumentParm(ERROR_TYPE_ARG_KEY)
}
