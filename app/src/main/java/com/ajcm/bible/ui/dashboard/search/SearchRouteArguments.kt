package com.ajcm.bible.ui.dashboard.search

import androidx.navigation.NavType
import androidx.navigation.navArgument

const val SEARCH_BY_ARG_KEY = "SEARCH_BY_ARG"
const val SEARCH_WITH_ARG_KEY = "SEARCH_WITH_ARG"

enum class SearchType {
    LANGUAGE,
    ALL
}

val allowedSearchArguments = listOf(
    navArgument(SEARCH_BY_ARG_KEY) {
        type = NavType.StringType
        nullable = true
        defaultValue = SearchType.ALL.name
    },
    navArgument(SEARCH_WITH_ARG_KEY) {
        type = NavType.StringType
        nullable = true
    }
)
