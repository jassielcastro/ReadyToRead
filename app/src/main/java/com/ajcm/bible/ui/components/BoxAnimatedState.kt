package com.ajcm.bible.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.theme.MaterialBibleTheme

enum class BoxState { Collapsed, Expanded }

private class TransitionData(
    size: State<Dp>
) {
    val size by size
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedSurface(
    collapsedContent: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit
) {
    var surfaceState by remember { mutableStateOf(BoxState.Collapsed) }
    val transitionData = updateTransitionData(surfaceState)
    Surface(
        color = MaterialBibleTheme.colors.gray,
        shape = MaterialBibleTheme.shapes.shapeMedium,
        modifier = Modifier
            .padding(horizontal = MaterialBibleTheme.dimensions.normal)
            .fillMaxWidth()
            .heigWrapContentIfApply(transitionData)
            .bounceClick(
                onClicked = {
                    surfaceState =
                        if (surfaceState == BoxState.Collapsed) BoxState.Expanded else BoxState.Collapsed
                }
            )
            .clip(MaterialBibleTheme.shapes.shapeMedium)
    ) {
        AnimatedContent(targetState = surfaceState) { targetState ->
            when (targetState) {
                BoxState.Collapsed -> collapsedContent()
                BoxState.Expanded -> expandedContent()
            }
        }
    }
}

private fun Modifier.heigWrapContentIfApply(data: TransitionData) = composed {
    if (data.size.value == 0f) {
        this.wrapContentHeight()
    } else {
        this.size(data.size)
    }
}

@Composable
private fun updateTransitionData(boxState: BoxState): TransitionData {
    val transition = updateTransition(boxState, label = "transition")
    val size = transition.animateDp(label = "animatedSize") { state ->
        when (state) {
            BoxState.Collapsed -> MaterialBibleTheme.dimensions.xxxlarge
            BoxState.Expanded -> MaterialBibleTheme.dimensions.zero
        }
    }
    return remember(transition) { TransitionData(size) }
}
