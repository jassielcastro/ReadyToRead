package com.ajcm.bible

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ajcm.domain.usecase.bible.GetBibleUc
import com.ajcm.domain.usecase.bible.GetBiblesUc
import com.ajcm.domain.usecase.book.GetBookUC
import com.ajcm.domain.usecase.book.GetBooksUc
import com.ajcm.domain.usecase.chapter.GetChapterUc
import com.ajcm.domain.usecase.chapter.GetChaptersUc
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getBiblesUc: GetBiblesUc
    @Inject
    lateinit var getBibleUc: GetBibleUc
    @Inject
    lateinit var getBooksUc: GetBooksUc
    @Inject
    lateinit var getBookUc: GetBookUC
    @Inject
    lateinit var getChaptersUc: GetChaptersUc
    @Inject
    lateinit var getChapterUc: GetChapterUc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }

        getBibles()
    }

    @Preview
    @Composable
    private fun Content() {
        MaterialTheme {
            TopAppBar(title = { Text(text = "My Bible") })
        }
    }

    private fun getBibles() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getBiblesUc()
            }.onSuccess {
                println("MainActivity.getBibles --> $it")
                getBible(it.first().id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getBible(bibleId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getBibleUc(bibleId)
            }.onSuccess {
                println("MainActivity.getBible --> $it")
                getBibleBooks(it.id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getBibleBooks(bibleId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getBooksUc(bibleId)
            }.onSuccess {
                println("MainActivity.getBibleBooks --> $it")
                getBibleBook(it.first().bibleId, it.first().id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getBibleBook(bibleId: String, bookId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getBookUc(bibleId, bookId)
            }.onSuccess {
                println("MainActivity.getBibleBook --> $it")
                getBookChapters(bibleId, bookId)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getBookChapters(bibleId: String, bookId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getChaptersUc(bibleId, bookId)
            }.onSuccess {
                println("MainActivity.getBookChapters --> $it")
                getBookChapter(bibleId, it.first().id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getBookChapter(bibleId: String, chapterId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                getChapterUc(bibleId, chapterId)
            }.onSuccess {
                println("MainActivity.getBookChapter --> $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}
