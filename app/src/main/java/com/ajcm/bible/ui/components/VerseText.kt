package com.ajcm.bible.ui.components

/*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ajcm.design.fonts.poppinsFamily
import com.ajcm.design.fonts.smallTextSize
import com.ajcm.design.fonts.xSmallTextSize
import com.ajcm.design.theme.PinkLight
import com.ajcm.design.theme.Pink
import com.ajcm.domain.entity.Verse

@Composable
fun VerseText(content: Verse, modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Start, lineHeight = 32.sp)) {
                withStyle(
                    style = SpanStyle(
                        color = PinkLight,
                        fontSize = xSmallTextSize,
                        fontFamily = poppinsFamily,
                        baselineShift = BaselineShift.Superscript
                    )
                ) {
                    append("${content.verseCount} ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Pink,
                        fontSize = smallTextSize,
                        fontFamily = poppinsFamily
                    )
                ) {
                    append(content.content)
                }
            }
        }, modifier = modifier.padding(horizontal = 16.dp)
    )
    Spacer(
        modifier = Modifier
            .requiredHeight(16.dp)
    )
}
*/