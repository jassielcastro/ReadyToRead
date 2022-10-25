package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.SearchBar
import com.ajcm.design.component.largeSpace
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchScreen(viewModel: SearchViewModel, arguments: Bundle?, actions: DashboardActions) {
    val initialSearch = arguments?.getString(SEARCH_WITH_ARG_KEY)?.takeIf {
        it != DashboardActions.NONE
    } ?: ""

    val foundBibles by viewModel.foundBibles.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (searchComponent, list) = createRefs()

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
                initialSearch,
                onTextChange = {
                    viewModel.search(it)
                },
                onBack = {
                    actions.upPress()
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(list) {
                    top.linkTo(searchComponent.bottom)
                }
        ) {

            normalSpace()

            item {
                if (foundBibles.isEmpty()) {
                    ErrorScreen(
                        bundleOf(
                            ERROR_TITLE_ARG_KEY to "No se encontraron resultados",
                            ERROR_MESSAGE_ARG_KEY to "Intenta con un criterio diferente",
                            ERROR_TYPE_ARG_KEY to ErrorType.EMPTY.name
                        )
                    )
                }
            }

            items(
                foundBibles.size,
                key = { index ->
                    foundBibles[index].id
                }
            ) { index ->
                CardBookItem(foundBibles[index])
            }

            largeSpace()
        }
    }
}
