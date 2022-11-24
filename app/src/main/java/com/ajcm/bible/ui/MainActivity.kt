package com.ajcm.bible.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ajcm.bible.ui.dashboard.DashboardScreen
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.reading.BibleDetailsAppBar
import com.ajcm.design.component.SearchBar
import com.ajcm.design.component.SmallSpacer
import com.ajcm.design.screen.BibleScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar(
                label = "Page title",
                initialSearch = "Barra de búsqueda",
                modifier = Modifier
            )

            SmallSpacer()

            BibleDetailsAppBar(
                title = "Page title",
                subTitle = "Barra de búsqueda sasdasdasdasdassdaasdasdasdasdasda",
                actions = DashboardActions(rememberNavController())
            )
        }
    }
}
