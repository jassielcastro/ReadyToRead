package com.ajcm.bible.ui.error

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun ErrorScreen(arguments: Bundle? = null) {

    val title = arguments?.getString(ERROR_TITLE_ARG_KEY) ?: ""
    val message = arguments?.getString(ERROR_MESSAGE_ARG_KEY) ?: ""
    val errorType = arguments?.getString(ERROR_TYPE_ARG_KEY) ?: ErrorType.FAILED.name

    val errorIcon = try {
        ErrorType.valueOf(errorType)
    } catch (ignore: Exception) {
        ErrorType.FAILED
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialBibleTheme.dimensions.normal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = errorIcon.drawable),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(fraction = 0.3f)
                .padding(vertical = MaterialBibleTheme.dimensions.normal)
        )

        Text(
            text = title,
            color = MaterialBibleTheme.colors.black,
            style = MaterialBibleTheme.typography.subtitle,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(MaterialBibleTheme.dimensions.medium))

        Text(
            text = message,
            color = MaterialBibleTheme.colors.black.copy(alpha = 0.6f),
            style = MaterialBibleTheme.typography.caption
        )
    }
}
