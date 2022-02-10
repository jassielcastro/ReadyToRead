package com.kavak.design

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BibleAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController
)

@Composable
fun rememberBibleAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController()
): BibleAppState = remember(scaffoldState, navController) {
    BibleAppState(scaffoldState, navController)
}
