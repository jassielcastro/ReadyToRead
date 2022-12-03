package com.ajcm.bible.ui.dashboard.sections

import androidx.compose.runtime.Immutable
import com.ajcm.domain.entity.Bible

@Immutable
data class SectionContentModel(
    val isLoading: Boolean = true,
    val languages: List<String> = emptyList(),
    val bibles: List<Bible> = emptyList()
)
