package com.ajcm.design.common

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.ajcm.design.theme.MaterialBibleTheme

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.bounceClick(
    onCLicked: () -> Unit,
    onLongClick: () -> Unit = {}
) = composed {
    var buttonState by remember { mutableStateOf(false) }
    val springSpec = SpringSpec<Float>(
        stiffness = 200f,
        dampingRatio = 0.8f
    )
    val scale by animateFloatAsState(if (buttonState) 0.95f else 1f, springSpec)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
            clip = true
        }
        .combinedClickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                color = MaterialBibleTheme.colors.green
            ),
            onClick = { onCLicked() },
            onLongClick = { onLongClick() }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState) {
                    waitForUpOrCancellation()
                    false
                } else {
                    awaitFirstDown(false)
                    true
                }
            }
        }
}
