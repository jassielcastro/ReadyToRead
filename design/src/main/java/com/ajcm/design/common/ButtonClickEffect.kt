package com.ajcm.design.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
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

fun Modifier.bounceClick(onCLicked: () -> Unit) = composed {
    var buttonState by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (buttonState) 0.9f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                color = MaterialBibleTheme.colors.green,
                bounded = false
            ),
            onClick = { onCLicked() }
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
