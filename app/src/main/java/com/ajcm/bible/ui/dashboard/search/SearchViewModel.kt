package com.ajcm.bible.ui.dashboard.search

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
class SearchViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mFoundBibles = MutableSharedFlow<List<Bible>>()
    val foundBibles = mFoundBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    fun search(by: String) = viewModelScope.launch {
        val bibles = withContext(Dispatchers.IO) {
            getBiblesUC.getAll(
                GetBibleRequest { query = by }
            )
        }

        mFoundBibles.emit(bibles)
    }

}
