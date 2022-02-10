package com.ajcm.bible.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.ajcm.bible.R
import com.kavak.design.theme.Aqua
import com.kavak.design.theme.White

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .background(Aqua)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .width(dimensionResource(R.dimen.min_logo_size))
                .height(dimensionResource(R.dimen.min_logo_size)),
            painter = painterResource(id = R.drawable.ic_api_logo),
            contentDescription = null,
            tint = White
        )
    }
}
