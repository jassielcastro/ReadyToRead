package com.ajcm.design.common

sealed class State {

    object Loading : State()
    data class Success<T>(var value: T) : State()
    data class Failure(val throwable: Throwable? = null) : State()
    object Empty : State()

}
