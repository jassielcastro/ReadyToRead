package com.ajcm.bible.ui.dashboard.sections

import com.ajcm.domain.entity.Bible

data class SectionContentModel(
    val isLoading: Boolean = true,
    val languages: List<String> = emptyList(),
    val bibles: List<Bible> = emptyList()
)
