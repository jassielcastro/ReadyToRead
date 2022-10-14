package com.ajcm.design.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

typealias NavigateBuilder = NavigationItems.Builder.() -> Unit

val NavigateBuilder.fullRoute: String
    get() = NavigationItems.Builder().apply(this).build().route

@SuppressLint("ComposableNaming")
@Composable
fun NavController.safeNavigateTo(
    route: NavigateBuilder,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    LaunchedEffect(Unit) {
        navigate(route = navigationRoute(route), navOptions, navigatorExtras)
    }
}

fun navigationRoute(route: NavigateBuilder): String {
    return route.fullRoute
}
