package com.ajcm.bible.ui.dashboard.favorite

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.dashboard.search.SearchListScreen
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.R
import com.ajcm.design.component.BottomSheetContainer
import com.ajcm.design.component.SearchBar
import com.ajcm.design.component.largeSpace
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme
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
    val foundBibles by viewModel.favoriteBibles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.downloadFavorites()
    }

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
            SearchBar(
                label = stringResource(id = R.string.favorites_title),
                readOnly = true,
                onBack = {
                    actions.upPress()
                }
            )
        }

        if (foundBibles.isNotEmpty()) {
            ShowBibles(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(toolbar.bottom)
                    },
                bibles = foundBibles,
                showBibleSheet = showBibleSheet
            )
        } else {
            ShowEmptyState()
        }
    }
}

@Composable
private fun ShowBibles(modifier: Modifier, bibles: List<Bible>, showBibleSheet: (Bundle) -> Unit) {
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
        bundleOf(
            ERROR_TITLE_ARG_KEY to "Sin favoritos",
            ERROR_MESSAGE_ARG_KEY to "Aún no has agregado libros a tus favoritos.",
            ERROR_TYPE_ARG_KEY to ErrorType.WARNING.name
        )
    )
}
