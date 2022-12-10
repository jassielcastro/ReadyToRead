package com.ajcm.bible.ui.dashboard.detail

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.*
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.theme.MaterialBibleTheme
import com.ajcm.bible.ui.theme.toLocalColor
import com.ajcm.bible.ui.theme.toLocalImage
import com.ajcm.domain.entity.Bible
import de.charlex.compose.HtmlText

const val BIBLE_ID_KEY = "bible_id_key"

@Composable
fun BibleDetail(
    bundle: Bundle,
    viewModel: SharedBibleViewModel,
    actions: DashboardActions
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val id = bundle.getString(BIBLE_ID_KEY)

        val bibleDetail by viewModel.bibleDetail.collectAsState()

        LaunchedEffect(id) {
            viewModel.getBibleDetail(id)
        }

        if (bibleDetail != null) {
            BibleDetailContent(bibleDetail!!, viewModel, actions)
        } else {
            LoadBibleDetailShimmer()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BibleDetailContent(
    bible: Bible,
    viewModel: SharedBibleViewModel,
    actions: DashboardActions
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialBibleTheme.dimensions.normal)
    ) {

        val color = bible.color.toLocalColor()
        val colorButtonBG = MaterialBibleTheme.colors.gray
        val favoriteIcon =
            if (bible.isFavourite) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_line

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
                image = ImageVector.vectorResource(id = bible.image.toLocalImage())
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
                    .bounceClick(onClicked = { actions.onBack() })
                    .padding(MaterialBibleTheme.dimensions.small)
            )

            Image(
                painter = painterResource(id = favoriteIcon),
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
                    .bounceClick(
                        onClicked = {
                            viewModel.toggleFavorite(bible.id)
                        }
                    )
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
                        top.linkTo(imageBook.bottom, 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                actions.onBack()
                viewModel.runWithDelay { actions.showReading(bible) }
            }
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
