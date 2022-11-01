package com.ajcm.bible.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.common.bounceClick
import com.ajcm.design.R
import com.ajcm.design.component.Circle
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.design.theme.toColor
import com.ajcm.design.theme.transformToImage
import com.ajcm.domain.entity.Bible

@Composable
fun CardBookItem(
    bible: Bible,
    onCardClicked: (bibleId: String) -> Unit,
    onFavClicked: (bibleId: String) -> Unit
) {
    val color = bible.color.toColor()
    val bookMark =
        if (bible.isFavourite) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_line

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = MaterialBibleTheme.dimensions.normal)
            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
            .bounceClick {
                onCardClicked(bible.id)
            }
            .clip(MaterialBibleTheme.shapes.shapeMedium)
    ) {
        val (imageBook, content, button) = createRefs()
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
            Circle(color = color)

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

        Image(
            painter = painterResource(id = bookMark),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialBibleTheme.dimensions.medium)
                .size(MaterialBibleTheme.dimensions.xlarge)
                .constrainAs(button) {
                    top.linkTo(parent.top, (-6).dp)
                    end.linkTo(parent.end)
                }
                .padding(MaterialBibleTheme.dimensions.xsmall)
                .bounceClick {
                    onFavClicked(bible.id)
                }
        )
    }
}
