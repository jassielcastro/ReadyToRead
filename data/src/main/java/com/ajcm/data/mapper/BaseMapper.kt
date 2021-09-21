package com.ajcm.data.mapper

interface BaseMapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}
