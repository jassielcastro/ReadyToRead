package com.ajcm.domain.entity

data class Search(
    val verseCount: Int,
    val verses: SearchVerse,
    val passage: List<Passage>
)
