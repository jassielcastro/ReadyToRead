package com.ajcm.verse.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajcm.design.theme.AquaDark
import com.ajcm.design.theme.AquaMedium
import com.ajcm.design.theme.Sandy

@Composable
fun VerseText(verseNumber: String, verse: String, modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Start, lineHeight = 32.sp)) {
                withStyle(
                    style = SpanStyle(
                        color = AquaDark,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("$verseNumber ")
                }
                withStyle(
                    style = SpanStyle(
                        color = AquaMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                ) {
                    append(verse)
                }
            }
        }, modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    Divider(color = Sandy, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 16.dp))
}
