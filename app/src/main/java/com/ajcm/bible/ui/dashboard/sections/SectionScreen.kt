package com.ajcm.bible.ui.dashboard.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ajcm.bible.R
import com.ajcm.bible.ui.dashboard.search.SearchType
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.design.component.*
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Bible

val languages = listOf(
    "Español",
    "Inglés",
    "Portugués",
    "Hindi",
    "Ruso",
    "Latín"
)

val bibles = listOf(
    Bible {
        name = "The New Testament in Paliya Language"
        nameLocal = "भगवान ना खरला बोल, नवलो नेम"
    },
    Bible {
        name = "Biblia Livre Para Todos"
        nameLocal = "Biblia Livre Para Todos"
    },
    Bible {
        name = "The New Testament in Pogoro"
        nameLocal = "Lipatanu Lya Syayi Kwa Wantu Woseri"
    },
    Bible {
        name = "Reina Valera"
        nameLocal = "Reina Valera 1960"
    }
)

val images = listOf(
    R.drawable.ic_bibliophile_amico,
    R.drawable.ic_book_lover_pana,
    R.drawable.ic_book_lover_rafiki,
    R.drawable.ic_ebook_pana,
    R.drawable.ic_ebook_rafiki
)

@Composable
fun SectionsScreen(actions: DashboardActions) {
    Column {

        CardInfoSection(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            actions.showSearchBy(type = SearchType.ALL)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            normalSpace()

            item {
                TextSections(text = "Idiomas") {
                    actions.showSearchBy(type = SearchType.LANGUAGE)
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
                            actions.showSearchBy(type = SearchType.LANGUAGE, lang)
                        }
                    }

                    mediumSpace()
                }
            }

            normalSpace()

            item {
                TextSections(text = "Selección de Biblias") {
                    actions.showSearchBy(type = SearchType.ALL)
                }
            }

            items(bibles.size) { index ->
                val bible = bibles[index]
                CardBookItem(
                    title = bible.name,
                    realName = bible.nameLocal,
                    background = MaterialBibleTheme.colors.random(),
                    image = images.random()
                )
            }
        }
    }
}
