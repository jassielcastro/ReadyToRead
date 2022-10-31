package com.ajcm.design.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun TextSections(text: String, onClick: () -> Unit) {
    val margin = MaterialBibleTheme.dimensions.normal
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = margin)
    ) {
        val (section, seeMore) = createRefs()
        Text(
            text = text,
            style = MaterialBibleTheme.typography.section,
            color = MaterialBibleTheme.colors.black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(section) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(seeMore.start)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = stringResource(id = R.string.see_more),
            style = MaterialBibleTheme.typography.subCaption,
            color = MaterialBibleTheme.colors.green,
            modifier = Modifier
                .constrainAs(seeMore) {
                    end.linkTo(parent.end, margin)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .bounceClick(onClick)
                .clip(MaterialBibleTheme.shapes.shapeSmall)
                .padding(horizontal = MaterialBibleTheme.dimensions.medium, vertical = MaterialBibleTheme.dimensions.small)
        )
    }
}
