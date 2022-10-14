package com.ajcm.design.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.ajcm.design.theme.MaterialBibleTheme

fun LazyListScope.mediumSpace() {
    item {
        Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.medium))
    }
}

fun LazyListScope.normalSpace() {
    item {
        Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.normal))
    }
}

fun LazyListScope.largeSpace() {
    item {
        Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.xxxlarge))
    }
}
