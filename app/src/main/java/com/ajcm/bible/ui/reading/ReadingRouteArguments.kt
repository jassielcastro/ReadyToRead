package com.ajcm.bible.ui.reading

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ajcm.bible.ui.components.navigation.NavigationItems
import com.ajcm.bible.ui.components.navigation.navigationRoute

const val BIBLE_ID_ARG_KEY = "BIBLE_ID_ARG_KEY"
const val BIBLE_TITLE_ARG_KEY = "BIBLE_TITLE_ARG_KEY"
const val BIBLE_SUBTITLE_ARG_KEY = "BIBLE_SUBTITLE_ARG_KEY"

val readingDestination = navigationRoute {
    destination = NavigationItems.Item.READING
    addArgumentParm(BIBLE_ID_ARG_KEY)
    addArgumentParm(BIBLE_TITLE_ARG_KEY)
    addArgumentParm(BIBLE_SUBTITLE_ARG_KEY)
}

val allowedReadingBibleArguments = listOf(
    navArgument(BIBLE_ID_ARG_KEY) {
        type = NavType.StringType
        nullable = true
    },
    navArgument(BIBLE_TITLE_ARG_KEY) {
        type = NavType.StringType
        nullable = true
    },
    navArgument(BIBLE_SUBTITLE_ARG_KEY) {
        type = NavType.StringType
        nullable = true
    }
)
