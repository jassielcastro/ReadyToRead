package com.ajcm.bible.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.design.theme.toColor
import com.ajcm.design.theme.transformToImage
import com.ajcm.domain.entity.Bible

@Composable
fun CardBookItem(
    bible: Bible,
    onCardClicked: (bible: Bible) -> Unit,
    onCardLongClicked: (bibleId: String) -> Unit
) {
    val color = bible.color.toColor()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = MaterialBibleTheme.dimensions.normal)
            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
            .bounceClick(
                onClicked = {
                    onCardClicked(bible)
                },
                onLongClick = {
                    onCardLongClicked(bible.id)
                }
            )
            .clip(MaterialBibleTheme.shapes.shapeMedium)
    ) {
        val (imageBook, content) = createRefs()
        Surface(
            color = color.copy(alpha = 0.7f),
            shape = MaterialBibleTheme.shapes.startShape,
            modifier = Modifier
                .width(MaterialBibleTheme.dimensions.cardInfo)
                .constrainAs(imageBook) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottom_waves),
                alignment = Alignment.BottomStart,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Image(
                imageVector = ImageVector.vectorResource(id = bible.image.transformToImage().resource),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(MaterialBibleTheme.dimensions.medium)
            )
        }

        Surface(
            color = MaterialBibleTheme.colors.gray,
            shape = MaterialBibleTheme.shapes.endShape,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imageBook.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialBibleTheme.dimensions.normal)
            ) {
                Text(
                    text = bible.nameLocal,
                    style = MaterialBibleTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )
                Text(
                    text = bible.name,
                    style = MaterialBibleTheme.typography.subCaption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = bible.getRegions(),
                    style = MaterialBibleTheme.typography.subCaption2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
