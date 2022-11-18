package com.ajcm.bible.ui.reading

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ajcm.bible.R
import com.ajcm.bible.ui.reading.viewmodel.ReadingViewModel
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.MediumSpacer
import com.ajcm.design.component.NormalSpacer
import com.ajcm.design.component.SmallSpacer
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme
import kotlinx.coroutines.launch
import java.util.*

sealed class ReadingStep(val step: Int) {
    data class Books(val bibleId: String) : ReadingStep(1)
    data class Chapters(val bibleId: String, val bookId: String) : ReadingStep(2)
    data class Verses(val bibleId: String, val bookId: String, val chapterId: String) : ReadingStep(3)
}

data class BookToRead(
    val id: String,
    val title: String,
    val subTitle: String
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReadingScreen(viewModel: ReadingViewModel, arguments: Bundle?) {
    val bookToRead by remember {
        mutableStateOf(
            BookToRead(
                id = arguments?.getString(BIBLE_ID_ARG_KEY) ?: "",
                title = arguments?.getString(BIBLE_TITLE_ARG_KEY) ?: "",
                subTitle = arguments?.getString(BIBLE_SUBTITLE_ARG_KEY) ?: ""
            )
        )
    }

    var subTitle by remember { mutableStateOf(bookToRead.subTitle) }
    var step by remember { mutableStateOf<ReadingStep>(ReadingStep.Books(bookToRead.id)) }

    BackHandler(enabled = step.step > 1) {
        if (step is ReadingStep.Verses) {
            val versesStep = (step as ReadingStep.Verses)
            step = ReadingStep.Chapters(versesStep.bibleId, versesStep.bookId)
        } else if (step is ReadingStep.Chapters) {
            val chaptersStep = (step as ReadingStep.Chapters)
            step = ReadingStep.Books(chaptersStep.bibleId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialBibleTheme.colors.white)
    ) {

        Surface(
            elevation = MaterialBibleTheme.dimensions.elevationSmall,
            color = MaterialBibleTheme.colors.white,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = MaterialBibleTheme.dimensions.normal)
            ) {

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.5f)
                        .bounceClick { }
                        .padding(MaterialBibleTheme.dimensions.medium)
                        .clip(MaterialBibleTheme.shapes.shapeLarge)
                )

                Column(
                    modifier = Modifier
                        .weight(7f)
                ) {
                    Text(
                        text = bookToRead.title,
                        style = MaterialBibleTheme.typography.section,
                    )

                    Text(
                        text = subTitle,
                        style = MaterialBibleTheme.typography.caption,
                    )
                }

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.5f)
                        .bounceClick { }
                        .padding(MaterialBibleTheme.dimensions.medium)
                        .clip(MaterialBibleTheme.shapes.shapeLarge)
                )
            }
        }

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                if (targetState.step > initialState.step) {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(600),
                    ) with fadeOut(
                        animationSpec = tween(600)
                    )
                } else {
                    fadeIn(
                        animationSpec = tween(600),
                    ) with slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(600)
                    )
                }.apply {
                    targetContentZIndex = targetState.step.toFloat()
                }
            }
        ) { screen ->
            when (screen) {
                is ReadingStep.Books -> {
                    BookListScreen(screen.bibleId, viewModel) { bookId ->
                        step = ReadingStep.Chapters(screen.bibleId, bookId)
                    }
                }
                is ReadingStep.Chapters -> {
                    subTitle = screen.bookId
                    ChaptersListScreen(
                        bibleId = screen.bibleId,
                        bookId = screen.bookId,
                        viewModel
                    ) { chapterId ->
                        step = ReadingStep.Verses(screen.bibleId, screen.bookId, chapterId)
                    }
                }
                is ReadingStep.Verses -> {
                    subTitle = screen.chapterId
                    VersesListScreen(
                        bibleId = screen.bibleId,
                        chapterId = screen.chapterId,
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun BookListScreen(
    bibleId: String,
    viewModel: ReadingViewModel,
    showChapters: (bookId: String) -> Unit
) {
    val books by viewModel.books.collectAsState()

    LaunchedEffect(bibleId) {
        viewModel.downloadBooks(bibleId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        SmallSpacer()

        books.forEach { book ->
            Text(
                text = book.nameLong,
                style = MaterialBibleTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .bounceClick { showChapters(book.id) }
                    .padding(MaterialBibleTheme.dimensions.normal)
            )
        }

        SmallSpacer()
    }
}

@Composable
fun ChaptersListScreen(
    bibleId: String,
    bookId: String,
    viewModel: ReadingViewModel,
    showVerses: (chapterId: String) -> Unit
) {
    val chapters by viewModel.chapters.collectAsState()

    LaunchedEffect(bibleId, bookId) {
        viewModel.downloadChapters(bibleId, bookId)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(chapters) { chapter ->
            Text(
                text = chapter.number.capitalize(Locale.getDefault()),
                style = MaterialBibleTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .bounceClick { showVerses(chapter.id) }
                    .clip(MaterialBibleTheme.shapes.shapeXLarge)
                    .padding(
                        horizontal = MaterialBibleTheme.dimensions.medium,
                        vertical = MaterialBibleTheme.dimensions.xxlarge
                    )
            )
        }
    }
}

@Composable
fun VersesListScreen(bibleId: String, chapterId: String, viewModel: ReadingViewModel) {
    val chapter by viewModel.chapter.collectAsState()

    LaunchedEffect(bibleId, chapterId) {
        viewModel.downloadChapter(bibleId, chapterId)
    }

    LazyColumn {

        normalSpace()

        itemsIndexed(chapter.content.split("\n".toRegex())) { index: Int, verse: String ->
            if (verse.isNotEmpty()) {
                VerseText(verse, index + 1)
            }
        }

        normalSpace()
    }
}

@Composable
fun VerseText(verse: String, number: Int) {
    val textColor = MaterialBibleTheme.colors.black
    val font = MaterialBibleTheme.typography.caption.fontFamily

    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Start)) {
                withStyle(
                    style = SpanStyle(
                        color = textColor,
                        fontSize = 10.sp,
                        fontFamily = font,
                        fontWeight = FontWeight.Bold,
                        baselineShift = BaselineShift.Superscript
                    )
                ) {
                    append("$number ")
                }
                withStyle(
                    style = SpanStyle(
                        color = textColor,
                        fontSize = 14.sp,
                        fontFamily = font
                    )
                ) {
                    append(verse)
                }
            }
        }, modifier = Modifier
            .padding(
                horizontal = MaterialBibleTheme.dimensions.normal,
                vertical = MaterialBibleTheme.dimensions.medium
            )
    )
}
