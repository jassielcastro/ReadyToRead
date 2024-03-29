package com.ajcm.bible.ui.components.common

sealed class State {

    object Loading : State()
    data class Success<T>(val value: T) : State()
    data class Failure(val throwable: Throwable? = null) : State()
    object Empty : State()

}
