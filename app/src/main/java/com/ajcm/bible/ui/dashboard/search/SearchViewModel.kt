package com.ajcm.bible.ui.dashboard.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.design.common.State
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mFoundBibles = MutableSharedFlow<State>()
    val foundBibles = mFoundBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State.Loading
        )

    private var searchJob: Job? = null
    private var currentQuery: String? = null

    fun search(by: String) = viewModelScope.launch {
        if (by == currentQuery) return@launch
        currentQuery = by
        searchJob?.cancelAndJoin()
        searchJob = viewModelScope.launch {
            mFoundBibles.emit(State.Loading)
            delay(300L)
            val bibles = withContext(Dispatchers.IO) {
                getBiblesUC.getAll(
                    GetBibleRequest { query = by }
                )
            }

            if (bibles.isEmpty()) {
                mFoundBibles.emit(State.Empty)
            } else {
                mFoundBibles.emit(State.Success(bibles))
            }
        }
    }

}
