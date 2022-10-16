package com.ajcm.design.navigation

class NavigationItems private constructor(val route: String) {

    enum class Item {
        SPLASH,
        ERROR,
        DASHBOARD,
        SECTIONS,
        FAVORITES,
        SEARCH,
        NONE
    }

    class Builder {

        var destination: Item = Item.NONE
        private var arguments: String = ""

        fun addArgumentParm(value: String) {
            arguments = arguments.plus("/{$value}")
        }

        fun addArgumentValue(value: String) {
            arguments = arguments.plus("/$value")
        }

        fun build() = NavigationItems(destination.name.plus(arguments))
    }

}
