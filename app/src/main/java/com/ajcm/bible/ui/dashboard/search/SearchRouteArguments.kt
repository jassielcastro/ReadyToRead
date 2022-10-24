package com.ajcm.bible.ui.dashboard.search

import androidx.navigation.NavType
import androidx.navigation.navArgument

const val SEARCH_WITH_ARG_KEY = "SEARCH_WITH_ARG"

val allowedSearchArguments = listOf(
    navArgument(SEARCH_WITH_ARG_KEY) {
        type = NavType.StringType
        nullable = true
    }
)
