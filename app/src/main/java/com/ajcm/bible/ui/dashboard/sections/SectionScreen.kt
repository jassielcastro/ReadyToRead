package com.ajcm.bible.ui.dashboard.sections

import android.os.Bundle
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
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

@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel
) {
    BottomSheetContainer(
        sheetContent = { bundle ->
            BibleDetail(bundle, viewModel, actions)
        },
        content = { showBibleSheet ->
            SectionsScreen(
                actions = actions,
                viewModel = viewModel,
                showBibleSheet = showBibleSheet
            )
        }
    )
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SharedBibleViewModel,
    showBibleSheet: (Bundle) -> Unit
) {
    val context = LocalContext.current
    val motionSceneContent = remember {
        context.resources
            .openRawResource(R.raw.splas_loader_scene)
            .readBytes()
            .decodeToString()
    }

    val hasBibles by viewModel.downloadBibles.collectAsState()

    val progress by remember {
        derivedStateOf {
            when (hasBibles) {
                State.Loading -> 0
                else -> 1
            }.toFloat()
        }
    }
    val progression by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 320,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(Unit) {
        viewModel.downloadBiblesIfNeed()
    }

    MotionLayout(
        motionScene = MotionScene(motionSceneContent),
        progress = progression,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .layoutId("app_bar")
                .background(MaterialBibleTheme.colors.green)
        ) {

        }

        SearchComponent(
            modifier = Modifier
                .layoutId("search_bar")
        ) {
            actions.showSearchBy()
        }

        Image(
            painter = painterResource(id = R.drawable.ic_bibliophile_rafiki),
            contentDescription = "",
            modifier = Modifier
                .layoutId("image_loader")
        )

        SectionListScreen(
            modifier = Modifier
                .layoutId("books_list")
                .background(MaterialBibleTheme.colors.white),
            actions = actions,
            viewModel = viewModel,
            showBibleSheet = showBibleSheet
        )
    }
}

@Composable
private fun SectionListScreen(
    modifier: Modifier,
    actions: DashboardActions,
    viewModel: SharedBibleViewModel,
    showBibleSheet: (Bundle) -> Unit
) {
    val sectionContent by viewModel.sectionContent.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.runWithDelay {
            viewModel.buildBibleSections()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        mediumSpace()

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
