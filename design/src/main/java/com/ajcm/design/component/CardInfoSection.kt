package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun CardInfoSection(modifier: Modifier) {
    Surface(
        shape = MaterialBibleTheme.shapes.shapeLarge,
        color = MaterialBibleTheme.colors.primary,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(MaterialBibleTheme.dimensions.medium)
        ) {
            val (imageBook, text, button) = createRefs()

            Circle(color = MaterialBibleTheme.colors.secondary.copy(alpha = 0.8f))

            Image(
                painter = painterResource(id = R.drawable.ic_bibliophile_rafiki),
                contentDescription = "book",
                modifier = Modifier
                    .width(MaterialBibleTheme.dimensions.bookInfoWidth)
                    .wrapContentHeight()
                    .constrainAs(imageBook) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = "Find the best book of your interest",
                color = MaterialBibleTheme.colors.textPrimary,
                style = MaterialBibleTheme.typography.body,
                textAlign = TextAlign.End,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        end = MaterialBibleTheme.dimensions.normal,
                        bottom = MaterialBibleTheme.dimensions.normal
                    )
                    .wrapContentHeight()
                    .constrainAs(text) {
                        bottom.linkTo(button.top)
                        start.linkTo(imageBook.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(
                text = "Ver todo",
                color = MaterialBibleTheme.colors.textSecundary,
                style = MaterialBibleTheme.typography.button,
                modifier = Modifier
                    .padding(
                        end = MaterialBibleTheme.dimensions.normal,
                        bottom = MaterialBibleTheme.dimensions.normal
                    )
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .bounceClick()
                    .clip(MaterialBibleTheme.shapes.shapeNormal)
                    .background(MaterialBibleTheme.colors.button)
                    .padding(
                        horizontal = MaterialBibleTheme.dimensions.large,
                        vertical = MaterialBibleTheme.dimensions.small
                    )
                    .clickable {

                    }
            )
        }
    }
}
