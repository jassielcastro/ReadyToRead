package com.ajcm.bible.ui.dashboard.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajcm.design.component.*
import com.ajcm.design.navigation.NavigationItems
import com.ajcm.design.navigation.navigationRoute
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Bible
import com.ajcm.bible.R

val dashboardDestination = navigationRoute {
    destination = NavigationItems.Item.DASHBOARD
}

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
fun DashboardScreen() {
    Scaffold(
        topBar = {
            SearchComponent {
                println("<top>.DashboardScreen --> topBar click")
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialBibleTheme.colors.background,
                elevation = 0.dp
            ) {

            }
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                item {
                    CardInfoSection(
                        modifier = Modifier
                            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
                            .fillMaxWidth()
                    )
                }

                largeSpace()

                item {
                    TextSections(text = "Idiomas")
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
                            LanguageItem(title = languages[index])
                        }

                        mediumSpace()
                    }
                }

                normalSpace()

                item {
                    TextSections(text = "Selección de Biblias")
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
    )
}
