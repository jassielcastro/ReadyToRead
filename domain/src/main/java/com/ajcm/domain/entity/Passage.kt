package com.ajcm.domain.entity

data class Passage(
    val id: String,
    val bibleId: String,
    val orgId: String,
    val content: String,
    val reference: String,
    val verseCount: Int,
    val copyright: String
)
