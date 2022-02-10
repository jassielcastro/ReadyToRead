package com.ajcm.bible.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ajcm.bible.R
import com.kavak.design.component.TitleText
import com.kavak.design.theme.White

@Preview
@Composable
fun SplashScreen() {
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
}
