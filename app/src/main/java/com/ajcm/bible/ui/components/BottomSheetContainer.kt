package com.ajcm.bible.ui.components

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import com.ajcm.bible.ui.theme.MaterialBibleTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContainer(
    sheetContent: @Composable (bundle: Bundle) -> Unit,
    content: @Composable (showBibleSheet: (Bundle) -> Unit) -> Unit
) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var bundle by remember { mutableStateOf(bundleOf()) }

    BackHandler(enabled = state.isVisible) {
        scope.launch {
            state.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = { sheetContent(bundle) },
        sheetShape = MaterialBibleTheme.shapes.topShape,
        sheetBackgroundColor = MaterialBibleTheme.colors.white,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialBibleTheme.colors.white)
    ) {
        content(
            showBibleSheet = { data ->
                scope.launch {
                    bundle = data
                    state.show()
                }
            }
        )
    }
}
