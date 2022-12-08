package com.ajcm.bible.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.theme.MaterialBibleTheme

@Composable
fun SmallRoundedItem(title: String, onClick: (String) -> Unit = {}) {
    Surface(
        shape = MaterialBibleTheme.shapes.shapeSmall,
        elevation = MaterialBibleTheme.dimensions.zero,
        color = MaterialBibleTheme.colors.gray,
        modifier = Modifier
            .clip(MaterialBibleTheme.shapes.shapeSmall)
            .bounceClick(
                onClicked = {
                    onClick(title)
                }
            )
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
