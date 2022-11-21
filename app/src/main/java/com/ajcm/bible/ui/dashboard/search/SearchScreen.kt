package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.R
import com.ajcm.design.common.State
import com.ajcm.design.component.*
import com.ajcm.domain.entity.Bible

@Composable
fun SearchScreen(viewModel: SharedBibleViewModel, arguments: Bundle?, actions: DashboardActions) {
    BottomSheetContainer(
        sheetContent = { bundle ->
            BibleDetail(bundle, viewModel, actions)
        },
        content = { showBibleSheet ->
            SearchListScreen(viewModel, arguments, actions, showBibleSheet)
        }
    )
}

@Composable
fun SearchListScreen(
    viewModel: SharedBibleViewModel,
    arguments: Bundle?,
    actions: DashboardActions,
    showBibleSheet: (Bundle) -> Unit
) {
    val initialSearch by remember {
        mutableStateOf(
            arguments?.getString(SEARCH_WITH_ARG_KEY)?.takeIf {
                it != DashboardActions.NONE
            } ?: ""
        )
    }

    val foundBibles by viewModel.foundBibles.collectAsState(State.Loading)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (searchComponent, list, shimmer) = createRefs()

        SearchBar(
            initialSearch = initialSearch,
            label = stringResource(id = R.string.search_by_hint_2),
            hint = stringResource(id = R.string.search_by_hint),
            onTextChange = {
                viewModel.search(it)
            },
            onBack = {
                actions.upPress()
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(searchComponent) {
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        )

        when (foundBibles) {
            State.Loading -> {
                LoadBiblesShimmer(
                    modifier = Modifier
                        .constrainAs(shimmer) {
                            top.linkTo(searchComponent.bottom)
                        }
                )
            }
            is State.Success<*> -> {
                ShowBibles(
                    modifier = Modifier
                        .constrainAs(list) {
                            top.linkTo(searchComponent.bottom)
                        },
                    bibles = (foundBibles as State.Success<*>).value as List<Bible>,
                    showBibleSheet = showBibleSheet
                )
            }
            State.Empty, is State.Failure -> {
                ShowEmptyState()
            }
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
            ERROR_TITLE_ARG_KEY to "No se encontraron resultados",
            ERROR_MESSAGE_ARG_KEY to "Intenta con un criterio diferente",
            ERROR_TYPE_ARG_KEY to ErrorType.EMPTY.name
        )
    )
}
