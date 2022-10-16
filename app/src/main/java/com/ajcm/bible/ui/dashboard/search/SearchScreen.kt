package com.ajcm.bible.ui.dashboard.search

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(arguments: Bundle?) {
    val type = arguments?.getString(SEARCH_BY_ARG_KEY, SearchType.ALL.name)
    val paramValue = arguments?.getString(SEARCH_WITH_ARG_KEY)
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Search by $type and $paramValue")
    }
}
