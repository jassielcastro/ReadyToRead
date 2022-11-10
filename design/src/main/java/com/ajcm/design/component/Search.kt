package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val color = MaterialBibleTheme.colors.black.copy(alpha = 0.7f)
    Surface(
        shape = MaterialBibleTheme.shapes.shapeNormal,
        color = MaterialBibleTheme.colors.white,
        modifier = Modifier
            .padding(horizontal = MaterialBibleTheme.dimensions.medium)
            .padding(
                top = MaterialBibleTheme.dimensions.normal,
                bottom = MaterialBibleTheme.dimensions.small
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .then(modifier)
            .bounceClick(onClick)
            .clip(MaterialBibleTheme.shapes.shapeNormal)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xxlarge)
                    .padding(MaterialBibleTheme.dimensions.medium)
            )
            Text(
                text = stringResource(id = R.string.search_by_hint),
                style = MaterialBibleTheme.typography.caption,
                color = color
            )
        }
    }
}
