package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        shape = MaterialBibleTheme.shapes.shapeNormal,
        color = MaterialBibleTheme.colors.white.copy(alpha = 0.9f),
        modifier = Modifier
            .padding(horizontal = MaterialBibleTheme.dimensions.medium)
            .padding(
                top = MaterialBibleTheme.dimensions.normal,
                bottom = MaterialBibleTheme.dimensions.small
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .then(modifier)
            .bounceClick()
            .clickable {
                onClick()
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialBibleTheme.colors.black.copy(alpha = 0.7f)),
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xxlarge)
                    .padding(MaterialBibleTheme.dimensions.medium)
            )
            Text(
                text = "Search by book, language, region...",
                style = MaterialBibleTheme.typography.caption,
                color = MaterialBibleTheme.colors.black.copy(alpha = 0.7f)
            )
        }
    }
}
