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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ajcm.splash.R
import com.ajcm.splash.viewModel.SplashViewModel
import com.ajcm.design.component.TitleText
import com.ajcm.design.theme.White

@Composable
fun SplashScreen(
    navController: NavController
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

    val splashViewModel: SplashViewModel = hiltViewModel()
    val hasFavouriteBibles by splashViewModel.hasFavouriteBibles.collectAsState()
    if (hasFavouriteBibles) {
        println("<top>.SplashScreen --> SI contiene biblias favoritas")
    } else {
        println("<top>.SplashScreen --> NO contiene biblias favoritas")
    }
}
