package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.SearchBar
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchScreen(arguments: Bundle?) {
    val initialSearch = arguments?.getString(SEARCH_WITH_ARG_KEY)?.takeIf {
        it != DashboardActions.NONE
    } ?: ""
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (searchComponent) = createRefs()

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
                .padding(bottom = MaterialBibleTheme.dimensions.normal)
        ) {
            SearchBar(initialSearch) {
                println("<top>.SearchScreen --> $it")
            }
        }
    }
}
