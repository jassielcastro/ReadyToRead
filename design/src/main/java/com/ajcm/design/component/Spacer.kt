package com.ajcm.design.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
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

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.small))
}

@Composable
fun MediumSpacer() {
    Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.medium))
}

@Composable
fun NormalSpacer() {
    Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.normal))
}

@Composable
fun LargeSpacer() {
    Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.xxxlarge))
}
