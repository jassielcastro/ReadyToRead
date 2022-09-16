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
import com.ajcm.design.common.State
import com.ajcm.design.component.TitleText
import com.ajcm.design.theme.White
import com.ajcm.splash.R
import com.ajcm.splash.viewModel.SplashViewModel

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

        TitleText(text = stringResource(id = R.string.app_name))
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
