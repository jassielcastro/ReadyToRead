package com.ajcm.domain

data class Search(
    val verseCount: Int,
    val verses: SearchVerse,
    val passage: List<Passage>
)
