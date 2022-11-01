package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.common.State
import com.ajcm.design.component.LoadBiblesShimmer
import com.ajcm.design.component.SearchBar
import com.ajcm.design.component.largeSpace
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Bible

@Composable
fun SearchScreen(viewModel: SearchViewModel, arguments: Bundle?, actions: DashboardActions) {
    val initialSearch = remember {
        mutableStateOf(
            arguments?.getString(SEARCH_WITH_ARG_KEY)?.takeIf {
                it != DashboardActions.NONE
            } ?: ""
        )
    }

    val foundBibles by viewModel.foundBibles.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (searchComponent, list, shimmer) = createRefs()

        Surface(
            color = MaterialBibleTheme.colors.white,
            elevation = MaterialBibleTheme.dimensions.elevationSmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(searchComponent) {
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        ) {
            SearchBar(
                initialSearch.value,
                onTextChange = {
                    viewModel.search(it)
                },
                onBack = {
                    actions.upPress()
                }
            )
        }

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
                    viewModel
                )
            }
            State.Empty, is State.Failure -> {
                ShowEmptyState()
            }
        }
    }
}

@Composable
private fun ShowBibles(modifier: Modifier, bibles: List<Bible>, viewModel: SearchViewModel) {
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
            ERROR_TITLE_ARG_KEY to "No se encontraron resultados",
            ERROR_MESSAGE_ARG_KEY to "Intenta con un criterio diferente",
            ERROR_TYPE_ARG_KEY to ErrorType.EMPTY.name
        )
    )
}
