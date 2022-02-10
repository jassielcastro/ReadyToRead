package com.kavak.design.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kavak.design.theme.AquaDark

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
