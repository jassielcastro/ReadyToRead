package com.ajcm.bible.ui.dashboard.sections

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.core.os.bundleOf
import com.ajcm.bible.R
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel
) {
    BottomSheetContainer(
        sheetContent = { bundle ->
            BibleDetail(bundle, viewModel)
        },
        content = { showBibleSheet ->
            SectionListScreen(actions = actions, viewModel = viewModel, showBibleSheet = showBibleSheet)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SectionListScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel,
    showBibleSheet: (Bundle) -> Unit
) {
    val languages by viewModel.languages.collectAsState()
    val bibles by viewModel.recomendedBibles.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.buildBibleSections()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = listState
    ) {
        stickyHeader {
            CardInfoSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialBibleTheme.shapes.bottomShape)
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
                    SmallRoundedItem(title = languages[index]) { lang ->
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
                    showBibleSheet(bundleOf(BIBLE_ID_KEY to it))
                }
            )
        }
    }
}
