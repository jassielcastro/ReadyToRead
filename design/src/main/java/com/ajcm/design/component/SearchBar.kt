package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    initialSearch: String = "",
    label: String,
    hint: String = "",
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onBack: () -> Unit,
    modifier: Modifier
) {
    var text by rememberSaveable { mutableStateOf(initialSearch) }
    LaunchedEffect(Unit) {
        delay(200L) // wait for screen initialization
        onTextChange(text)
    }
    val grayColor = MaterialBibleTheme.colors.black.copy(alpha = 0.5f)
    Surface(
        color = MaterialBibleTheme.colors.white,
        elevation = MaterialBibleTheme.dimensions.elevationSmall,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = MaterialBibleTheme.dimensions.medium)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                colorFilter = ColorFilter.tint(grayColor),
                modifier = Modifier
                    .padding(top = MaterialBibleTheme.dimensions.small)
                    .size(MaterialBibleTheme.dimensions.xxlarge)
                    .padding(MaterialBibleTheme.dimensions.medium)
                    .bounceClick(onBack)
            )
            TextField(
                value = text,
                readOnly = readOnly,
                onValueChange = {
                    text = it
                    onTextChange(it)
                },
                textStyle = MaterialBibleTheme.typography.caption,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = grayColor,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = MaterialBibleTheme.colors.white,
                    unfocusedIndicatorColor = MaterialBibleTheme.colors.white,
                    cursorColor = MaterialBibleTheme.colors.green
                ),
                label = {
                    Text(
                        text = label,
                        style = MaterialBibleTheme.typography.subCaption,
                        color = grayColor.copy(alpha = 0.7f)
                    )
                },
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialBibleTheme.typography.caption,
                        color = grayColor.copy(alpha = 0.7f)
                    )
                },
                trailingIcon = {
                    if (text.isNotBlank())
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = "Limpiar campo de nombre",
                                tint = grayColor
                            )
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
