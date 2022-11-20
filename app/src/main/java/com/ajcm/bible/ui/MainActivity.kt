package com.ajcm.bible.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.reading.BibleDetailsAppBar
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
        BibleDetailsAppBar(
            title = "Reina Valera 1909",
            subTitle = "Reina Valera 1909",
            actions = DashboardActions(
                rememberNavController()
            )
        )
    }
}
