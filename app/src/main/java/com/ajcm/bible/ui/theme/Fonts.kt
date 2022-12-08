package com.ajcm.bible.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ajcm.bible.R

private val xBigTextSize = 52.sp
private val bigTextSize = 36.sp
private val normalTextSize = 24.sp
private val mediumTextSize = 18.sp
private val smallTextSize = 14.sp
private val xSmallTextSize = 12.sp

private val poppinsFamily = FontFamily(
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_thin, FontWeight.Thin)
)

private val infiniteStrockFamily = FontFamily(
    Font(R.font.infinite_stroke, FontWeight.Normal)
)

data class BibleTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = infiniteStrockFamily,
        fontWeight = FontWeight.Normal,
        fontSize = xBigTextSize
    ),
    val title: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = bigTextSize
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = normalTextSize
    ),
    val body: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = mediumTextSize
    ),
    val section: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = mediumTextSize
    ),
    val button: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = xSmallTextSize
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = smallTextSize
    ),
    val captionBold: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = smallTextSize
    ),
    val subCaption: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = xSmallTextSize
    ),
    val subCaption2: TextStyle = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = xSmallTextSize
    )
)

val LocalTypography = staticCompositionLocalOf { BibleTypography() }
