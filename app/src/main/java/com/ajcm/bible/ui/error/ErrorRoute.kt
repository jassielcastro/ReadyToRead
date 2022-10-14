package com.ajcm.bible.ui.error

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ajcm.bible.R
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigationRoute

const val ERROR_TITLE_ARG_KEY = "ERROR_TITLE_ARG"
const val ERROR_MESSAGE_ARG_KEY = "ERROR_MESSAGE_ARG"
const val ERROR_TYPE_ARG_KEY = "ERROR_TYPE_ARG"

val allowedErrorArguments = listOf(
    navArgument(ERROR_TITLE_ARG_KEY) {
        type = NavType.StringType
    },
    navArgument(ERROR_MESSAGE_ARG_KEY) {
        type = NavType.StringType
    },
    navArgument(ERROR_TYPE_ARG_KEY) {
        type = NavType.StringType
    }
)

enum class ErrorType(val drawable: Int) {
    WARNING(R.drawable.ic_bibliophile_rafiki),
    FAILED(R.drawable.ic_book_lover_pana),
    EMPTY(R.drawable.ic_book_lover_rafiki)
}

val errorscreenDestination = navigationRoute {
    destination = NavigationItems.Item.ERROR
    addArgumentParm(ERROR_TITLE_ARG_KEY)
    addArgumentParm(ERROR_MESSAGE_ARG_KEY)
    addArgumentParm(ERROR_TYPE_ARG_KEY)
}
