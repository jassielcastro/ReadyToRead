package com.ajcm.bible.ui.dashboard.sections

import android.os.Bundle
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.ajcm.bible.ui.components.CardBookItem
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.common.State
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel
) {
    val hasBibles by viewModel.downloadBibles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.downloadBiblesIfNeed()
    }

    Crossfade(
        targetState = hasBibles
    ) { state ->
        when (state) {
            State.Loading -> {
                SplashContent()
            }
            is State.Success<*> -> {
                BottomSheetContainer(
                    sheetContent = { bundle ->
                        BibleDetail(bundle, viewModel, actions)
                    },
                    content = { showBibleSheet ->
                        SectionListScreen(
                            actions = actions,
                            viewModel = viewModel,
                            showBibleSheet = showBibleSheet
                        )
                    }
                )
            }
            State.Empty, is State.Failure -> {
                ErrorScreen(
                    "Error al descargar",
                    "No fue posible descargar las biblias. Inténtalo más tarde.",
                    ErrorType.FAILED
                )
            }
        }
    }
}

@Composable
fun SplashContent() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialBibleTheme.colors.green))
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SectionListScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel,
    showBibleSheet: (Bundle) -> Unit
) {
    val sectionContent by viewModel.sectionContent.collectAsState()
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
                    .fillParentMaxHeight(0.35f)
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

                items(sectionContent.languages.size) { index ->
                    SmallRoundedItem(title = sectionContent.languages[index]) { lang ->
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

        items(sectionContent.bibles.size) { index ->
            CardBookItem(
                bible = sectionContent.bibles[index],
                onCardClicked = {
                    actions.showReading(it)
                },
                onCardLongClicked = {
                    showBibleSheet(bundleOf(BIBLE_ID_KEY to it))
                }
            )
        }
    }
}
