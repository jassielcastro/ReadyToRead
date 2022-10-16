package com.ajcm.bible.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ajcm.design.component.CardBookItem
import com.ajcm.design.screen.BibleScreen
import com.ajcm.design.theme.MaterialBibleTheme
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
        CardBookItem(
            title = "asdasdfasdf",
            realName = "sadfasdfasd",
            background = MaterialBibleTheme.colors.primary,
            image = 0
        )
    }
}
