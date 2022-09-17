package com.ajcm.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ajcm.design.common.State
import com.ajcm.design.component.LogoTitleText

import com.ajcm.design.theme.White
import com.ajcm.splash.R
import com.ajcm.splash.viewModel.SplashViewModel
import com.ajcm.verse.ui.VerseText

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel
) {
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(dimensionResource(R.dimen.min_logo_size))
                .height(dimensionResource(R.dimen.min_logo_size)),
            painter = painterResource(id = R.drawable.ic_open_book_logo),
            contentDescription = null
        )

        LogoTitleText(text = stringResource(id = R.string.app_name))


        VerseText(verseNumber = "1", verse = "In the beginning God created the heavens and the earth.")
        VerseText(verseNumber = "2", verse = "Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.")
        VerseText(verseNumber = "3", verse = "And God said, Let there be light, and there was light.")
    }

    val hasFavouriteBibles by splashViewModel.hasFavouriteBibles.collectAsState()
    when (hasFavouriteBibles) {
        is State.Success<*> -> {
            println("<top>.SplashScreen ---> Si tiene biblias")
        }
        State.Empty -> {
            println("<top>.SplashScreen ---> No tiene biblias")
        }
        else -> {
        }
    }
}
