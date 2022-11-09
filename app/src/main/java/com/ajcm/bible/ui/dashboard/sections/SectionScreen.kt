package com.ajcm.bible.ui.dashboard.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.CardBookItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SectionViewModel
) {

    val languages by viewModel.languages.collectAsState()
    val bibles by viewModel.selectedBibles.collectAsState()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = listState
    ) {

        stickyHeader {
            CardInfoSection(
                modifier = Modifier
                    .fillMaxWidth()
            ) { actions.showSearchBy() }
        }

        normalSpace()

        item {
            TextSections(text = stringResource(id = R.string.section_language)) {
                actions.showSearchBy()
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(MaterialBibleTheme.dimensions.medium),
                modifier = Modifier
                    .padding(
                        top = MaterialBibleTheme.dimensions.medium,
                        bottom = MaterialBibleTheme.dimensions.normal
                    )
                    .fillMaxWidth()
            ) {
                mediumSpace()

                items(languages.size) { index ->
                    LanguageItem(title = languages[index]) { lang ->
                        actions.showSearchBy(lang)
                    }
                }

                mediumSpace()
            }
        }

        normalSpace()

        item {
            TextSections(text = stringResource(id = R.string.section_bibles)) {
                actions.showSearchBy()
            }
        }

        items(bibles.size) { index ->
            CardBookItem(
                bible = bibles[index],
                onCardClicked = {

                }
            )
        }
    }
}
