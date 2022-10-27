package com.ajcm.design.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun LanguageItem(title: String, onClick: (String) -> Unit) {
    Surface(
        shape = MaterialBibleTheme.shapes.shapeSmall,
        elevation = MaterialBibleTheme.dimensions.zero,
        color = MaterialBibleTheme.colors.gray,
        modifier = Modifier
            .clip(MaterialBibleTheme.shapes.shapeSmall)
            .bounceClick {
                onClick(title)
            }
    ) {
        Text(
            text = title,
            color = MaterialBibleTheme.colors.black,
            style = MaterialBibleTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    horizontal = MaterialBibleTheme.dimensions.normal,
                    vertical = MaterialBibleTheme.dimensions.small
                )
        )
    }
}