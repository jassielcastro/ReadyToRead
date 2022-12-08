package com.ajcm.bible.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.ajcm.bible.R
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.theme.MaterialBibleTheme
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    initialSearch: String = "",
    label: String,
    hint: String = "",
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier
) {
    var text by rememberSaveable { mutableStateOf(initialSearch) }
    LaunchedEffect(Unit) {
        delay(200L) // wait for screen initialization
        onTextChange(text)
    }
    val blackColor = MaterialBibleTheme.colors.black
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
                .height(MaterialBibleTheme.dimensions.appBar)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        start = MaterialBibleTheme.dimensions.normal,
                        bottom = MaterialBibleTheme.dimensions.normal,
                        top = MaterialBibleTheme.dimensions.normal
                    )
                    .size(MaterialBibleTheme.dimensions.large)
                    .bounceClick(onClicked = onBack)
                    .clip(MaterialBibleTheme.shapes.shapeLarge)
            )
            TextField(
                value = text,
                readOnly = readOnly,
                enabled = !readOnly,
                onValueChange = {
                    text = it
                    onTextChange(it)
                },
                textStyle = MaterialBibleTheme.typography.subCaption2,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = blackColor,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = MaterialBibleTheme.colors.white,
                    unfocusedIndicatorColor = MaterialBibleTheme.colors.white,
                    cursorColor = MaterialBibleTheme.colors.green
                ),
                label = {
                    Text(
                        text = label,
                        style = MaterialBibleTheme.typography.caption,
                        color = blackColor
                    )
                },
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialBibleTheme.typography.subCaption2,
                        color = blackColor.copy(alpha = 0.7f)
                    )
                },
                trailingIcon = {
                    if (text.isNotBlank())
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = "Limpiar campo de nombre"
                            )
                        }
                },
                modifier = Modifier
                    .padding(
                        end = MaterialBibleTheme.dimensions.large,
                        start = MaterialBibleTheme.dimensions.medium
                    )
                    .fillMaxWidth()
            )
        }
    }
}
