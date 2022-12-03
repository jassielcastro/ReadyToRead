package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
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
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.ErrorScreen
import com.ajcm.bible.ui.error.ErrorType
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
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val foundBibles by viewModel.foundBibles.collectAsState(State.Loading)

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (searchComponent, list, shimmer) = createRefs()

            SearchBar(
                initialSearch = arguments?.getString(SEARCH_WITH_ARG_KEY)
                    ?.takeIf { it != DashboardActions.NONE } ?: "",
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
                        actions = actions,
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
        "No se encontraron resultados",
        "Intenta con un criterio diferente",
        ErrorType.EMPTY
    )
}
