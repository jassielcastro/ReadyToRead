package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        shape = MaterialBibleTheme.shapes.shapeXLarge,
        color = MaterialBibleTheme.colors.gray,
        modifier = Modifier
            .padding(MaterialBibleTheme.dimensions.normal)
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
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xlarge)
                    .padding(MaterialBibleTheme.dimensions.medium)
            )
            Text(
                text = "Search by book, language, region...",
                style = MaterialBibleTheme.typography.caption,
                color = MaterialBibleTheme.colors.textPrimary.copy(alpha = 0.5f)
            )
        }
    }
}
