package com.ajcm.bible.ui.dashboard.sections

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.ajcm.domain.entity.Bible

data class SectionBibleUIModel(
    val bible: Bible = Bible {  },
    @DrawableRes val image: Int = 0,
    val color: Color = Color.Transparent
)
