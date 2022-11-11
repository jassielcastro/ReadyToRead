package com.ajcm.bible.ui.dashboard.detail

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.design.theme.toColor
import com.ajcm.design.theme.transformToImage
import com.ajcm.domain.entity.Bible
import de.charlex.compose.HtmlText

const val BIBLE_ID_KEY = "bible_id_key"

@Composable
fun BibleDetail(bundle: Bundle, bibleDetailViewModel: BibleDetailViewModel) {
    val id = bundle.getString(BIBLE_ID_KEY)

    val bibleDetail by bibleDetailViewModel.bibleDetail.collectAsState()

    LaunchedEffect(id) {
        bibleDetailViewModel.getBibleDetail(id)
    }

    if (bibleDetail != null) {
        BibleDetailContent(bibleDetail!!)
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialBibleTheme.dimensions.xxxlarge)
        ) {
            CircularProgressIndicator(
                color = MaterialBibleTheme.colors.green,
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xxxlarge)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BibleDetailContent(bible: Bible) {
    val color = bible.color.toColor()
    val colorButtonBG = MaterialBibleTheme.colors.gray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialBibleTheme.dimensions.normal)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = MaterialBibleTheme.dimensions.medium)
        ) {
            val (imageBook, title, subTitle, readButton, closeButton, favoriteButton) = createRefs()

            BibleImage(
                modifier = Modifier
                    .constrainAs(imageBook) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(subTitle.bottom)
                        height = Dimension.fillToConstraints
                    },
                color = color,
                image = ImageVector.vectorResource(id = bible.image.transformToImage().resource)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_clear),
                contentDescription = "",
                modifier = Modifier
                    .size(MaterialBibleTheme.dimensions.xlarge)
                    .drawBehind { drawCircle(colorButtonBG) }
                    .clip(MaterialBibleTheme.shapes.shapeLarge)
                    .constrainAs(closeButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .bounceClick {  }
                    .padding(MaterialBibleTheme.dimensions.small)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_bookmark_line),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = MaterialBibleTheme.dimensions.medium)
                    .size(MaterialBibleTheme.dimensions.xlarge)
                    .drawBehind { drawCircle(colorButtonBG) }
                    .clip(MaterialBibleTheme.shapes.shapeLarge)
                    .constrainAs(favoriteButton) {
                        top.linkTo(closeButton.bottom)
                        end.linkTo(parent.end)
                    }
                    .bounceClick {  }
                    .padding(MaterialBibleTheme.dimensions.small)
            )

            Text(
                text = bible.name,
                style = MaterialBibleTheme.typography.section,
                modifier = Modifier
                    .padding(horizontal = MaterialBibleTheme.dimensions.medium)
                    .wrapContentHeight()
                    .constrainAs(title) {
                        start.linkTo(imageBook.end)
                        end.linkTo(favoriteButton.start)
                        top.linkTo(imageBook.top)
                        width = Dimension.fillToConstraints
                    }
            )

            Text(
                text = bible.nameLocal,
                style = MaterialBibleTheme.typography.caption,
                modifier = Modifier
                    .padding(horizontal = MaterialBibleTheme.dimensions.medium)
                    .wrapContentHeight()
                    .constrainAs(subTitle) {
                        start.linkTo(imageBook.end)
                        end.linkTo(favoriteButton.start)
                        top.linkTo(title.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

            FullRoundedItem(
                title = "Comenzar a leer",
                modifier = Modifier
                    .constrainAs(readButton) {
                        top.linkTo(imageBook.bottom, 12.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = MaterialBibleTheme.dimensions.small)
                .verticalScroll(rememberScrollState())
        ) {

            SmallSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lenguaje:",
                    style = MaterialBibleTheme.typography.captionBold,
                )

                SmallSpacer()

                Text(
                    text = bible.language.nameLocal,
                    style = MaterialBibleTheme.typography.caption,
                )
            }

            MediumSpacer()

            Text(
                text = "Países",
                style = MaterialBibleTheme.typography.captionBold,
                modifier = Modifier
                    .fillMaxWidth()
            )

            SmallSpacer()

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                bible.countries.forEach {
                    SmallRoundedItem(title = it.nameLocal)

                    SmallSpacer()
                }
            }

            MediumSpacer()

            Text(
                text = "Descripción",
                style = MaterialBibleTheme.typography.captionBold,
                modifier = Modifier
                    .fillMaxWidth()
            )

            SmallSpacer()

            HtmlText(
                text = bible.fullInformation(),
                style = MaterialBibleTheme.typography.caption,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )

            Divider(
                color = MaterialBibleTheme.colors.brownLight,
                modifier = Modifier
                    .padding(top = MaterialBibleTheme.dimensions.normal)
                    .fillMaxWidth()
            )

            Text(
                text = bible.copyright,
                style = MaterialBibleTheme.typography.subCaption,
                color = MaterialBibleTheme.colors.black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = MaterialBibleTheme.dimensions.normal)
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun BibleImage(
    modifier: Modifier,
    color: Color,
    image: ImageVector
) {
    Surface(
        color = color.copy(alpha = 0.7f),
        shape = MaterialBibleTheme.shapes.shapeNormal,
        modifier = Modifier
            .width(MaterialBibleTheme.dimensions.cardInfo)
            .then(modifier)
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
            imageVector = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(MaterialBibleTheme.dimensions.medium)
        )
    }
}
