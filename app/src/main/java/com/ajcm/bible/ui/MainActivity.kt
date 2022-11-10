package com.ajcm.bible.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajcm.design.component.CardInfoSection
import com.ajcm.design.component.SearchBar
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
        CardInfoSection(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialBibleTheme.shapes.bottomShape)
        ) {

        }
    }
}
