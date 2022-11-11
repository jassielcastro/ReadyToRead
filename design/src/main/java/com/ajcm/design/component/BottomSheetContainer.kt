package com.ajcm.design.component

import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.core.os.bundleOf
import com.ajcm.design.theme.MaterialBibleTheme
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

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = { sheetContent(bundle) },
        sheetShape = MaterialBibleTheme.shapes.topShape,
        sheetBackgroundColor = MaterialBibleTheme.colors.white,
    ) {
        content(showBibleSheet = { data ->
            scope.launch {
                bundle = data
                state.show()
            }
        })
    }
}
