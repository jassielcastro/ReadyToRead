package com.ajcm.bible.ui.dashboard.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.bible.R

@Composable
fun SectionsScreen(
    actions: DashboardActions,
    viewModel: SectionViewModel
) {

    val languages by viewModel.languages.collectAsState()
    val bibles by viewModel.selectedBibles.collectAsState()

    Column {
        CardInfoSection(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            actions.showSearchBy()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

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
                val bibleUI = bibles[index]
                CardBookItem(
                    title = bibleUI.bible.name,
                    realName = bibleUI.bible.nameLocal,
                    background = bibleUI.color,
                    image = bibleUI.image
                )
            }
        }
    }
}
