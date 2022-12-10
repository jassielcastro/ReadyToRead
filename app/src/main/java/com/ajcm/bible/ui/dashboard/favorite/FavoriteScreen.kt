package com.ajcm.bible.ui.dashboard.favorite

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.*
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.ErrorScreen
import com.ajcm.bible.ui.error.ErrorType
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.domain.entity.Bible

@Composable
fun FavoriteScreen(
    viewModel: SharedBibleViewModel,
    actions: DashboardActions
) {
    BottomSheetContainer(
        sheetContent = { bundle ->
            BibleDetail(bundle, viewModel, actions)
        },
        content = { showBibleSheet ->
            FavoriteListScreen(viewModel, actions, showBibleSheet)
        }
    )
}

@Composable
fun FavoriteListScreen(
    viewModel: SharedBibleViewModel,
    actions: DashboardActions,
    showBibleSheet: (Bundle) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val foundBibles by viewModel.favoriteBibles.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.downloadFavorites()
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (toolbar, list) = createRefs()

            SearchBar(
                label = stringResource(id = R.string.favorites_title),
                readOnly = true,
                onBack = {
                    actions.upPress()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(toolbar) {
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
            )

            if (foundBibles.isNotEmpty()) {
                ShowBibles(
                    modifier = Modifier
                        .constrainAs(list) {
                            top.linkTo(toolbar.bottom)
                        },
                    actions = actions,
                    bibles = foundBibles,
                    showBibleSheet = showBibleSheet
                )
            } else {
                ShowEmptyState()
            }
        }
    }
}

@Composable
private fun ShowBibles(
    modifier: Modifier,
    actions: DashboardActions,
    bibles: List<Bible>,
    showBibleSheet: (Bundle) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        state = rememberLazyListState()
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
                    actions.showReading(it)
                },
                onCardLongClicked = {
                    showBibleSheet(bundleOf(BIBLE_ID_KEY to it))
                }
            )
        }

        largeSpace()
    }
}

@Composable
private fun ShowEmptyState() {
    ErrorScreen(
        "Sin favoritos",
        "Aún no has agregado libros a tus favoritos.",
        ErrorType.WARNING
    )
}
