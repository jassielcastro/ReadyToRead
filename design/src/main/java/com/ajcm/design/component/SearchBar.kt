package com.ajcm.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ajcm.design.R
import com.ajcm.design.common.bounceClick
import com.ajcm.design.theme.MaterialBibleTheme

@Composable
fun SearchBar(initialSearch: String, onTextChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf(initialSearch) }
    val grayColor = MaterialBibleTheme.colors.black.copy(alpha = 0.5f)
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
                .bounceClick {

                }
        )
        TextField(
            value = text,
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
                    text = stringResource(id = R.string.search_by_hint_2),
                    style = MaterialBibleTheme.typography.subCaption,
                    color = grayColor.copy(alpha = 0.7f)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_by_hint),
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
            }
        )
    }
}
