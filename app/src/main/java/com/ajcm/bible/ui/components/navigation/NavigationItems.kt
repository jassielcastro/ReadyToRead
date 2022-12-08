package com.ajcm.bible.ui.components.navigation

class NavigationItems private constructor(val route: String) {

    enum class Item {
        MORE_MENU,
        SECTIONS,
        FAVORITES,
        READING,
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
