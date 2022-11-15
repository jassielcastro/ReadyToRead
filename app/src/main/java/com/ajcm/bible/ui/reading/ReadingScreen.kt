package com.ajcm.bible.ui.reading

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun ReadingScreen(arguments: Bundle?) {
    val bibleId by rememberSaveable {
        mutableStateOf(
            arguments?.getString(BIBLE_ID_ARG_KEY) ?: ""
        )
    }

    LaunchedEffect(bibleId) {

    }
}
