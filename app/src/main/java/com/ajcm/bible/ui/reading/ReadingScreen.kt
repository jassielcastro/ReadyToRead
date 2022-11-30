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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.ajcm.bible.R
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.reading.viewmodel.ReadingViewModel
import com.ajcm.design.common.bounceClick
import com.ajcm.design.component.LoadBooksShimmer
import com.ajcm.design.component.LoadChapterShimmer
import com.ajcm.design.component.SmallSpacer
import com.ajcm.design.component.normalSpace
import com.ajcm.design.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Book
import com.ajcm.domain.entity.Chapter

sealed class ReadingStep(val step: Int) {
    data class Books(val bibleId: String) : ReadingStep(1)
    data class Chapters(
        val bibleId: String,
        val bookId: String,
        val bookName: String
    ) : ReadingStep(2)

    data class Verses(
        val bibleId: String,
        val bookId: String,
        val chapterId: String,
        val bookName: String,
        val chapterName: String
    ) : ReadingStep(3)
}

@Immutable
data class BookToRead(
    val id: String = "",
    val title: String = "",
    val subTitle: String = ""
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReadingScreen(
    readingViewModel: ReadingViewModel,
    configurationViewModel: ConfigurationsViewModel,
    arguments: Bundle?,
    actions: DashboardActions
) {
    val bookToRead by remember {
        mutableStateOf(
            BookToRead(
                id = arguments?.getString(BIBLE_ID_ARG_KEY) ?: "",
                title = arguments?.getString(BIBLE_TITLE_ARG_KEY) ?: "",
                subTitle = arguments?.getString(BIBLE_SUBTITLE_ARG_KEY) ?: ""
            )
        )
    }

    val title by remember { mutableStateOf(bookToRead.title) }
    var subTitle by remember { mutableStateOf(bookToRead.subTitle) }
    var step by remember { mutableStateOf<ReadingStep>(ReadingStep.Books(bookToRead.id)) }

    BackHandler(enabled = step.step > 1) {
        if (step is ReadingStep.Verses) {
            val versesStep = (step as ReadingStep.Verses)
            step = ReadingStep.Chapters(versesStep.bibleId, versesStep.bookId, versesStep.bookName)
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
        BibleDetailsAppBar(title = title, subTitle = subTitle, actions = actions)

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                if (targetState.step > initialState.step) {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(300),
                    ) with fadeOut(
                        animationSpec = tween(300)
                    )
                } else {
                    fadeIn(
                        animationSpec = tween(300),
                    ) with slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }.apply {
                    targetContentZIndex = targetState.step.toFloat()
                }
            }
        ) { screen ->
            when (screen) {
                is ReadingStep.Books -> {
                    subTitle = bookToRead.subTitle
                    BookListScreen(screen.bibleId, readingViewModel) { bookId, bookName ->
                        step = ReadingStep.Chapters(screen.bibleId, bookId, bookName)
                    }
                }
                is ReadingStep.Chapters -> {
                    subTitle = screen.bookName
                    ChaptersListScreen(
                        bibleId = screen.bibleId,
                        bookId = screen.bookId,
                        readingViewModel
                    ) { chapterId, chapterNumber ->
                        step = ReadingStep.Verses(
                            screen.bibleId,
                            screen.bookId,
                            chapterId,
                            screen.bookName,
                            chapterNumber
                        )
                    }
                }
                is ReadingStep.Verses -> {
                    subTitle = "${screen.bookName}: ${screen.chapterName}"
                    VersesListScreen(
                        bibleId = screen.bibleId,
                        chapterId = screen.chapterId,
                        readingViewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BibleDetailsAppBar(title: String, subTitle: String, actions: DashboardActions) {
    Surface(
        color = MaterialBibleTheme.colors.white,
        elevation = MaterialBibleTheme.dimensions.elevationSmall,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialBibleTheme.dimensions.appBar)
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        start = MaterialBibleTheme.dimensions.normal,
                        bottom = MaterialBibleTheme.dimensions.normal,
                        top = MaterialBibleTheme.dimensions.normal
                    )
                    .size(MaterialBibleTheme.dimensions.large)
                    .bounceClick(onCLicked = { actions.onBack() })
                    .clip(MaterialBibleTheme.shapes.shapeLarge)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = MaterialBibleTheme.dimensions.medium)
                    .padding(horizontal = MaterialBibleTheme.dimensions.large)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialBibleTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                AnimatedContent(targetState = subTitle) { text ->
                    Text(
                        text = text,
                        style = MaterialBibleTheme.typography.subCaption2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BookListScreen(
    bibleId: String,
    viewModel: ReadingViewModel,
    showChapters: (bookId: String, bookName: String) -> Unit
) {
    val books by viewModel.books.collectAsState()

    LaunchedEffect(bibleId) {
        viewModel.downloadBooks(bibleId)
    }

    AnimatedContent(targetState = books) { bookList ->
        if (bookList.isNotEmpty()) {
            BookListScreen(books = bookList, showChapters = showChapters)
        } else {
            LoadBooksShimmer()
        }
    }
}

@Composable
fun BookListScreen(books: List<Book>, showChapters: (bookId: String, bookName: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = MaterialBibleTheme.dimensions.xsmall)
            .fillMaxSize()
            .background(MaterialBibleTheme.colors.white)
            .verticalScroll(rememberScrollState())
    ) {

        SmallSpacer()

        books.forEach { book ->
            Text(
                text = book.name,
                style = MaterialBibleTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .bounceClick(onCLicked = { showChapters(book.id, book.name) })
                    .padding(MaterialBibleTheme.dimensions.normal)
            )
        }

        SmallSpacer()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ChaptersListScreen(
    bibleId: String,
    bookId: String,
    viewModel: ReadingViewModel,
    showVerses: (chapterId: String, chapterNumber: String) -> Unit
) {
    val chapters by viewModel.chapters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.downloadChapters(bibleId, bookId)
    }

    AnimatedContent(targetState = chapters) { chapterList ->
        if (chapterList.isNotEmpty()) {
            ChapterListScreen(chapterList, showVerses)
        } else {
            LoadChapterShimmer()
        }
    }
}

@Composable
fun ChapterListScreen(
    chapters: List<Chapter>,
    showVerses: (chapterId: String, chapterNumber: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = MaterialBibleTheme.dimensions.xsmall)
            .background(MaterialBibleTheme.colors.white)
    ) {
        items(chapters) { chapter ->
            Text(
                text = chapter.number,
                style = MaterialBibleTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .bounceClick(onCLicked = { showVerses(chapter.id, chapter.number) })
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

    LaunchedEffect(chapterId) {
        viewModel.downloadChapter(bibleId, chapterId)
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = MaterialBibleTheme.dimensions.xsmall)
            .background(MaterialBibleTheme.colors.white)
    ) {

        normalSpace()

        itemsIndexed(chapter.getVerses()) { index: Int, verse: String ->
            if (verse.isNotEmpty()) {
                VerseText(verse, index + 1)
            }

            SmallSpacer()
        }

        normalSpace()
    }
}

@Composable
fun VerseText(verse: String, number: Int) {
    val textColor = MaterialBibleTheme.colors.black
    val fontTitle = MaterialBibleTheme.typography.h1.fontFamily
    val font = MaterialBibleTheme.typography.caption.fontFamily

    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                withStyle(
                    style = SpanStyle(
                        color = textColor,
                        fontSize = 14.sp,
                        fontFamily = font,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("$number    ")
                }

                if (number == 1) {
                    val verseSplited = verse.split("\\s".toRegex()).filter { it.isNotEmpty() }
                    val firstWord = verseSplited.first { it.trim().isNotEmpty() }
                    val completeVerse = verseSplited
                        .filterIndexed { index, _ -> index != 0 }
                        .joinToString(" ")
                        .removeSuffix("\n")
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = 32.sp,
                            fontFamily = fontTitle,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append(firstWord.first())
                    }
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = 14.sp,
                            fontFamily = font
                        )
                    ) {
                        append(firstWord.removeRange(0, 1))
                        append(" ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = 14.sp,
                            fontFamily = font
                        )
                    ) {
                        append(completeVerse)
                    }
                } else {
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
            }
        }, modifier = Modifier
            .padding(
                horizontal = MaterialBibleTheme.dimensions.normal,
                vertical = MaterialBibleTheme.dimensions.xsmall
            )
    )
}
