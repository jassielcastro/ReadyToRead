package com.ajcm.bible.ui.dashboard.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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

    private val mSelectedBibles = MutableSharedFlow<List<Bible>>()
    val selectedBibles = mSelectedBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
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
        mSelectedBibles.emit(bibles)
    }

}
