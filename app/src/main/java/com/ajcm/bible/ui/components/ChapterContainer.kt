package com.ajcm.bible.ui.components

/*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ajcm.design.fonts.poppinsFamily
import com.ajcm.design.fonts.bigTextSize
import com.ajcm.design.fonts.smallTextSize
import com.ajcm.design.theme.PinkLight
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.entity.Verse

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterContainer(book: String, chapter: Chapter, listOfVerses: List<Verse>, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = book,
            color = PinkLight,
            fontSize = bigTextSize,
            fontFamily = poppinsFamily,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
        Text(
            text = chapter.content,
            color = PinkLight,
            fontSize = smallTextSize,
            fontFamily = poppinsFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 4.dp, bottom = 24.dp)
        )
        LazyColumn(content = {
            items(listOfVerses) { item ->
                VerseText(content = item, modifier)
            }
        })
    }
}
*/