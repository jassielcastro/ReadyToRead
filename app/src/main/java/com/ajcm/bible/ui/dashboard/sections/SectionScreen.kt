package com.ajcm.bible.ui.dashboard.sections

import android.os.Bundle
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.ajcm.bible.ui.components.*
import com.ajcm.bible.ui.components.common.State
import com.ajcm.bible.ui.dashboard.detail.BIBLE_ID_KEY
import com.ajcm.bible.ui.dashboard.detail.BibleDetail
import com.ajcm.bible.ui.dashboard.viewmodels.SharedBibleViewModel
import com.ajcm.bible.ui.error.*
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.theme.MaterialBibleTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
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

                    items(sectionContent.languages, key = { it }) { language ->
                        SmallRoundedItem(title = language) { lang ->
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

            items(sectionContent.bibles, key = { it.id }) { bible ->
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
        }
    }
}
