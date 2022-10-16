package com.ajcm.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
            color = MaterialBibleTheme.colors.textPrimary,
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
            text = "Ver más",
            style = MaterialBibleTheme.typography.subCaption,
            color = MaterialBibleTheme.colors.textPrimary.copy(alpha = 0.7f),
            modifier = Modifier
                .constrainAs(seeMore) {
                    end.linkTo(parent.end, margin)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .bounceClick()
                .clickable {
                    onClick()
                }
        )
    }
}
