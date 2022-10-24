package com.ajcm.bible.ui.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.design.common.State
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mDownloadBibles = MutableSharedFlow<State>()
    val downloadBibles = mDownloadBibles
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            State.Loading
        )

    init {
        downloadBibles()
    }

    private fun downloadBibles() = viewModelScope.launch {
        val bibles = withContext(Dispatchers.IO) {
            getBiblesUC.getAll()
        }
        mDownloadBibles.emit(
            if (bibles.isNotEmpty()) {
                State.Success(bibles)
            } else {
                State.Empty
            }
        )
    }
}
