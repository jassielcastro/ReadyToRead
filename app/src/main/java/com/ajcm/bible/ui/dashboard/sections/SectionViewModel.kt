package com.ajcm.bible.ui.dashboard.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.bible.R
import com.ajcm.design.theme.randomColors
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mLanguages = MutableSharedFlow<List<String>>()
    val languages = mLanguages
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val mSelectedBibles = MutableSharedFlow<List<SectionBibleUIModel>>()
    val selectedBibles = mSelectedBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val images = listOf(
        R.drawable.ic_bibliophile_amico,
        R.drawable.ic_book_lover_pana,
        R.drawable.ic_book_lover_rafiki,
        R.drawable.ic_ebook_pana,
        R.drawable.ic_ebook_rafiki
    )

    companion object {
        const val LANGUAGE_SIZE = 7
        const val BIBLES_SIZE = 5
    }

    init {
        downloadBibles()
    }

    private fun downloadBibles() = viewModelScope.launch {
        val bibles = withContext(Dispatchers.IO) {
            getBiblesUC.getAll(GetBibleRequest { size = BIBLES_SIZE })
        }
        val languages = withContext(Dispatchers.IO) {
            getBiblesUC.getBibleLanguages(LANGUAGE_SIZE)
        }

        mLanguages.emit(languages)
        mSelectedBibles.emit(bibles.mapToSectionBibleUIList())
    }

    private fun List<Bible>.mapToSectionBibleUIList(): List<SectionBibleUIModel> {
        return this
            .map {
                SectionBibleUIModel(
                    bible = it,
                    image = images.random(),
                    color = randomColors.random()
                )
            }
    }
}
