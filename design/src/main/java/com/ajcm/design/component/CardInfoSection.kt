package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun CardInfoSection(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        color = MaterialBibleTheme.colors.green,
        modifier = modifier
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_waves),
            contentDescription = "",
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(MaterialBibleTheme.dimensions.normal)
        ) {
            val (imageBook, search, text, button) = createRefs()

            SearchComponent(
                modifier = Modifier
                    .constrainAs(search) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                onClick()
            }

            Image(
                painter = painterResource(id = R.drawable.ic_bibliophile_rafiki),
                contentDescription = "",
                modifier = Modifier
                    .width(MaterialBibleTheme.dimensions.bookInfo)
                    .wrapContentHeight()
                    .constrainAs(imageBook) {
                        start.linkTo(parent.start)
                        top.linkTo(search.bottom, 8.dp)
                    }
            )

            Text(
                text = stringResource(id = R.string.section_header),
                color = MaterialBibleTheme.colors.white,
                style = MaterialBibleTheme.typography.section,
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
                        bottom.linkTo(button.top, 8.dp)
                        start.linkTo(imageBook.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(
                        end = MaterialBibleTheme.dimensions.normal,
                        bottom = MaterialBibleTheme.dimensions.normal
                    )
                    .constrainAs(button) {
                        bottom.linkTo(imageBook.bottom)
                        end.linkTo(parent.end)
                    }
                    .bounceClick(onClick)
                    .clip(MaterialBibleTheme.shapes.shapeNormal)
                    .background(MaterialBibleTheme.colors.white)
                    .padding(
                        horizontal = MaterialBibleTheme.dimensions.small,
                        vertical = MaterialBibleTheme.dimensions.small
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    color = MaterialBibleTheme.colors.green,
                    style = MaterialBibleTheme.typography.button,
                    modifier = Modifier
                        .padding(start = MaterialBibleTheme.dimensions.medium)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    tint = MaterialBibleTheme.colors.green,
                    modifier = Modifier
                        .padding(start = MaterialBibleTheme.dimensions.medium)
                        .rotate(180f)
                )
            }
        }
    }
}
