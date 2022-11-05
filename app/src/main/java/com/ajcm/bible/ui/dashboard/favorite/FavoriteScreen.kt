package com.ajcm.bible.ui.dashboard.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.error.*
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.largeSpace
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Bible

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel) {

    val foundBibles by viewModel.foundBibles.collectAsState()
    val grayColor = MaterialBibleTheme.colors.black.copy(alpha = 0.5f)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (toolbar, list) = createRefs()

        Surface(
            color = MaterialBibleTheme.colors.white,
            elevation = MaterialBibleTheme.dimensions.elevationSmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(toolbar) {
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = MaterialBibleTheme.dimensions.medium)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(grayColor),
                    modifier = Modifier
                        .padding(top = MaterialBibleTheme.dimensions.small)
                        .size(MaterialBibleTheme.dimensions.xxlarge)
                        .padding(MaterialBibleTheme.dimensions.medium)
                        .bounceClick {

                        }
                )

                Text(
                    text = stringResource(id = R.string.favorites_title),
                    style = MaterialBibleTheme.typography.subCaption,
                    color = grayColor.copy(alpha = 0.7f)
                )
            }
        }

        if (foundBibles.isNotEmpty()) {
            ShowBibles(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(toolbar.bottom)
                    },
                bibles = foundBibles,
                viewModel
            )
        } else {
            ShowEmptyState()
        }
    }
}

@Composable
private fun ShowBibles(modifier: Modifier, bibles: List<Bible>, viewModel: FavoriteViewModel) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        state = listState
    ) {

        normalSpace()

        items(
            bibles,
            key = { bible ->
                bible.id
            }
        ) { bible ->
            CardBookItem(
                bible = bible,
                onCardClicked = {

                },
                onFavClicked = {
                    viewModel.toggleFavorite(it)
                }
            )
        }

        largeSpace()
    }
}

@Composable
private fun ShowEmptyState() {
    ErrorScreen(
        bundleOf(
            ERROR_TITLE_ARG_KEY to "Sin favoritos",
            ERROR_MESSAGE_ARG_KEY to "Aún no has agregado libros a tus favoritos.",
            ERROR_TYPE_ARG_KEY to ErrorType.WARNING.name
        )
    )
}
