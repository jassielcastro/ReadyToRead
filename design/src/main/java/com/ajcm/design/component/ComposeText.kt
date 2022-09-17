package com.ajcm.design.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ajcm.design.theme.AquaDark

@Composable
fun LogoTitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 32.sp,
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 24.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun NormalText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun NormalBoldText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun NormalThinText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = AquaDark,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Thin,
        modifier = modifier
    )
}