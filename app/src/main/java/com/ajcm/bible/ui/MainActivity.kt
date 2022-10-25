package com.ajcm.bible.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ajcm.bible.ui.dashboard.search.SearchScreen
import com.ajcm.design.screen.BibleScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupAppUI()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {

    }
}
