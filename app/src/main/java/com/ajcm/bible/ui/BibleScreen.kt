package com.ajcm.bible.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.ajcm.bible.ui.theme.BibleComposeTheme

@Composable
fun BibleScreen(content: @Composable () -> Unit) {
    BibleComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
