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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.compose.rememberNavController
import com.ajcm.bible.R
import com.ajcm.bible.ui.dashboard.configuration.TextResizeScreen
import com.ajcm.bible.ui.dashboard.viewmodels.ConfigurationsViewModel
import com.ajcm.bible.ui.navigation.DashboardActions
import com.ajcm.bible.ui.reading.viewmodel.ReadingViewModel
import com.ajcm.bible.ui.BibleScreen
import com.ajcm.bible.ui.components.*
import com.ajcm.bible.ui.components.common.bounceClick
import com.ajcm.bible.ui.theme.MaterialBibleTheme
import com.ajcm.domain.entity.Book
import com.ajcm.domain.entity.Chapter

const val SHOW_BOTTOM_SHEET_TEXT = "show_text"

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
    BottomSheetContainer(
        sheetContent = { bundle ->
            if (bundle.getBoolean(SHOW_BOTTOM_SHEET_TEXT, false)) {
                TextResizeScreen(configurationViewModel)
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialBibleTheme.dimensions.xsmall)
                        .background(MaterialBibleTheme.colors.white)
                )
            }
        },
        content = { showSheet ->
            ReadingScreen(
                arguments,
                actions,
                configurationViewModel,
                readingViewModel,
                showSheet
            )
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReadingScreen(
    arguments: Bundle?,
    actions: DashboardActions,
    configurationViewModel: ConfigurationsViewModel,
    readingViewModel: ReadingViewModel,
    showSheet: (Bundle) -> Unit = {}
) {

    val bookToRead = BookToRead(
        id = arguments?.getString(BIBLE_ID_ARG_KEY) ?: "",
        title = arguments?.getString(BIBLE_TITLE_ARG_KEY) ?: "",
        subTitle = arguments?.getString(BIBLE_SUBTITLE_ARG_KEY) ?: ""
    )

    var step by remember { mutableStateOf<ReadingStep>(ReadingStep.Books(bookToRead.id)) }
    val showResizeAction by remember {
        derivedStateOf {
            step is ReadingStep.Verses
        }
    }

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
        var subTitle by remember { mutableStateOf(bookToRead.subTitle) }

        BibleDetailsAppBar(
            title = bookToRead.title,
            subTitle = subTitle,
            actions = actions,
            showResizeAction,
            showSheet
        )

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                slideIf { targetState.step > initialState.step }
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
                        readingViewModel,
                        configurationViewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BibleDetailsAppBar(
    title: String,
    subTitle: String,
    actions: DashboardActions,
    showConfigurations: Boolean,
    showSheet: (Bundle) -> Unit = {}
) {
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
                    .bounceClick(onClicked = { actions.onBack() })
                    .clip(MaterialBibleTheme.shapes.shapeLarge)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = MaterialBibleTheme.dimensions.medium)
                    .padding(horizontal = MaterialBibleTheme.dimensions.large)
                    .fillMaxWidth(0.85f)
            ) {
                Text(
                    text = title,
                    style = MaterialBibleTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                AnimatedContent(
                    targetState = subTitle,
                    transitionSpec = { slideIf { targetState != initialState } }
                ) { text ->
                    Text(
                        text = text,
                        style = MaterialBibleTheme.typography.subCaption2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }

            AnimatedVisibility(showConfigurations) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_format_size),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            end = MaterialBibleTheme.dimensions.normal,
                            bottom = MaterialBibleTheme.dimensions.normal,
                            top = MaterialBibleTheme.dimensions.normal
                        )
                        .size(MaterialBibleTheme.dimensions.large)
                        .bounceClick(onClicked = {
                            showSheet(
                                bundleOf(
                                    SHOW_BOTTOM_SHEET_TEXT to true
                                )
                            )
                        })
                        .clip(MaterialBibleTheme.shapes.shapeLarge)
                )
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
    Box(modifier = Modifier.fillMaxSize()) {
        val books by viewModel.books.collectAsState()

        LaunchedEffect(bibleId) {
            viewModel.downloadBooks(bibleId)
        }

        Crossfade(targetState = books) { bookList ->
            if (bookList.isNotEmpty()) {
                BookListScreen(books = bookList, showChapters = showChapters)
            } else {
                LoadBooksShimmer()
            }
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
                    .clip(MaterialBibleTheme.shapes.shapeMedium)
                    .bounceClick(onClicked = { showChapters(book.id, book.name) })
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
    Box(modifier = Modifier.fillMaxSize()) {
        val chapters by viewModel.chapters.collectAsState()

        SideEffect {
            viewModel.downloadChapters(bibleId, bookId)
        }

        Crossfade(
            targetState = chapters
        ) { chapterList ->
            if (chapterList.isNotEmpty()) {
                ChapterListScreen(chapterList, showVerses)
            } else {
                LoadChapterShimmer()
            }
        }
    }
}

@Composable
fun ChapterListScreen(
    chapters: List<Chapter>,
    showVerses: (chapterId: String, chapterNumber: String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = MaterialBibleTheme.dimensions.xsmall)
            .fillMaxSize()
            .background(MaterialBibleTheme.colors.white)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(chapters, key = { it.id }) { chapter ->
                Text(
                    text = chapter.number,
                    style = MaterialBibleTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(MaterialBibleTheme.shapes.shapeMedium)
                        .bounceClick(onClicked = { showVerses(chapter.id, chapter.number) })
                        .padding(
                            horizontal = MaterialBibleTheme.dimensions.medium,
                            vertical = MaterialBibleTheme.dimensions.xxlarge
                        )
                )
            }
        }
    }
}

@Composable
fun VersesListScreen(
    bibleId: String,
    chapterId: String,
    viewModel: ReadingViewModel,
    configurationViewModel: ConfigurationsViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val chapter by viewModel.chapter.collectAsState()
        val configurations by configurationViewModel.configurations.collectAsState()

        LaunchedEffect(chapterId) {
            viewModel.downloadChapter(bibleId, chapterId)
            configurationViewModel.getConfigurations()
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = MaterialBibleTheme.dimensions.xsmall)
                .fillMaxSize()
                .background(MaterialBibleTheme.colors.white)
        ) {

            normalSpace()

            itemsIndexed(chapter.getVerses()) { index: Int, verse: String ->
                if (verse.isNotEmpty()) {
                    VerseText(verse, index + 1, configurations?.textSizeMultiplier ?: 3)
                }

                MediumSpacer()
            }

            normalSpace()
        }
    }
}

@Composable
fun VerseText(verse: String, number: Int, textSizeMultiplier: Int) {
    val textColor = MaterialBibleTheme.colors.black
    val fontTitle = MaterialBibleTheme.typography.h1.fontFamily
    val font = MaterialBibleTheme.typography.caption.fontFamily

    var normalFontSize by remember { mutableStateOf((8 * textSizeMultiplier).sp) }
    var titleFontSize by remember { mutableStateOf((16 * textSizeMultiplier).sp) }

    SideEffect {
        normalFontSize = (8 * textSizeMultiplier).sp
        titleFontSize = (16 * textSizeMultiplier).sp
    }

    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                withStyle(
                    style = SpanStyle(
                        color = textColor,
                        fontSize = normalFontSize,
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
                            fontSize = titleFontSize,
                            fontFamily = fontTitle,
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append(firstWord.first())
                    }
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = normalFontSize,
                            fontFamily = font
                        )
                    ) {
                        append(firstWord.removeRange(0, 1))
                        append(" ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = normalFontSize,
                            fontFamily = font
                        )
                    ) {
                        append(completeVerse)
                    }
                } else {
                    withStyle(
                        style = SpanStyle(
                            color = textColor,
                            fontSize = normalFontSize,
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

@OptIn(ExperimentalAnimationApi::class)
fun slideIf(isFromLeft: () -> Boolean): ContentTransform {
    return if (isFromLeft()) {
        slideInHorizontally(
            animationSpec = tween(300),
            initialOffsetX = { fullWidth -> fullWidth }
        ) with slideOutHorizontally(
            animationSpec = tween(300),
            targetOffsetX = { fullWidth -> -fullWidth }
        )
    } else {
        slideInHorizontally(
            animationSpec = tween(300),
            initialOffsetX = { fullWidth -> -fullWidth }
        ) with slideOutHorizontally(
            animationSpec = tween(300),
            targetOffsetX = { fullWidth -> fullWidth }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComponent() {
    BibleScreen {
        Column {
            BibleDetailsAppBar(
                title = "Biblia",
                subTitle = "Subtitle",
                actions = DashboardActions(rememberNavController()),
                showConfigurations = true
            )

            LoadChapterShimmer()
        }
    }
}

