package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun CardBookItem(title: String, realName: String, background: Color, image: Int) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = MaterialBibleTheme.dimensions.normal)
            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
            .bounceClick()
    ) {
        val (imageBook, content) = createRefs()
        Surface(
            color = background.copy(alpha = 0.7f),
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
            Circle(color = background)

            Image(
                painter = painterResource(id = image),
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
                    text = title,
                    style = MaterialBibleTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.small))
                Text(
                    text = realName,
                    style = MaterialBibleTheme.typography.subCaption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
