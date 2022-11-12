package com.ajcm.bible.ui.dashboard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.design.common.State
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.entity.request.GetBibleRequest
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SharedBibleViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mLanguages = MutableSharedFlow<List<String>>()
    val languages = mLanguages
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val mRecomendedBibles = MutableSharedFlow<List<Bible>>()
    val recomendedBibles = mRecomendedBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private val mBibleDetail = MutableStateFlow<Bible?>(null)
    val bibleDetail = mBibleDetail
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            mBibleDetail.value
        )

    private val mFavoriteBibles = MutableSharedFlow<List<Bible>>()
    val favoriteBibles = mFavoriteBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    private val mFoundBibles = MutableSharedFlow<State>()
    val foundBibles = mFoundBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State.Loading
        )
    private var searchJob: Job? = null
    private var currentQuery: String? = null

    fun buildBibleSections() = viewModelScope.launch {
        val bibles = withContext(Dispatchers.IO) {
            recomendedBibles.value.ifEmpty {
                getBiblesUC.getAll(
                    GetBibleRequest {
                        size = BIBLES_SIZE
                        ortedBy = GetBibleRequest.OrderType.RANDOM
                    }
                )
            }
        }

        val languages = withContext(Dispatchers.IO) {
            languages.value.ifEmpty {
                getBiblesUC.getBibleLanguages(LANGUAGE_SIZE)
            }
        }

        mLanguages.emit(languages)
        mRecomendedBibles.emit(bibles)
    }

    fun getBibleDetail(bibleId: String?) = viewModelScope.launch {
        mBibleDetail.emit(null)
        runCatching {
            withContext(Dispatchers.IO) {
                getBiblesUC.getBible(bibleId)
            }
        }.onSuccess {
            mBibleDetail.emit(it)
        }.onFailure {
            mBibleDetail.emit(null)
        }
    }

    fun downloadFavorites() = viewModelScope.launch {
        val bibles = withContext(Dispatchers.IO) {
            getBiblesUC.getAll(
                GetBibleRequest { favorite = GetBibleRequest.Favorite.TRUE }
            )
        }

        mFavoriteBibles.emit(bibles)
    }

    fun search(by: String) = viewModelScope.launch {
        if (by == currentQuery) return@launch
        currentQuery = by
        searchJob?.cancelAndJoin()
        searchJob = viewModelScope.launch {
            mFoundBibles.emit(State.Loading)
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

    private companion object {
        const val LANGUAGE_SIZE = 7
        const val BIBLES_SIZE = 5
    }

}
