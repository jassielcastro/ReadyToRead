package com.ajcm.bible.ui.reading.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.domain.entity.Book
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.usecase.book.BooksUc
import com.ajcm.domain.usecase.chapter.ChaptersUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReadingViewModel @Inject constructor(
    private val booksUc: BooksUc,
    private val chaptersUc: ChaptersUc
) : ViewModel() {

    private val mBooks = MutableSharedFlow<List<Book>>()
    val books = mBooks
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val mChapters = MutableSharedFlow<List<Chapter>>()
    val chapters = mChapters
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val mChapter = MutableSharedFlow<Chapter>()
    val chapter = mChapter
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Chapter { }
        )

    fun downloadBooks(bibleId: String) = viewModelScope.launch {
        val downloadedBooks = books.value.ifEmpty {
            withContext(Dispatchers.IO) {
                booksUc.getBooksOf(bibleId)
            }
        }
        mBooks.emit(downloadedBooks)
    }

    fun downloadChapters(bibleId: String, bookId: String) = viewModelScope.launch {
        mChapters.emit(emptyList())
        val chapters = withContext(Dispatchers.IO) {
            chaptersUc.getChapters(bibleId, bookId)
        }
        mChapters.emit(chapters)
    }

    fun downloadChapter(bibleId: String, chapterId: String) = viewModelScope.launch {
        mChapter.emit(Chapter { })
        val chapter = withContext(Dispatchers.IO) {
            chaptersUc.getChapter(bibleId, chapterId)
        }
        mChapter.emit(chapter)
    }
}
