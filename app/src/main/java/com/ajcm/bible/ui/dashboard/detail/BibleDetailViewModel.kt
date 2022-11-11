package com.ajcm.bible.ui.dashboard.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.domain.entity.Bible
import com.ajcm.domain.usecase.bible.GetBiblesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BibleDetailViewModel @Inject constructor(
    private val getBiblesUC: GetBiblesUc
) : ViewModel() {

    private val mBibleDetail = MutableStateFlow<Bible?>(null)
    val bibleDetail = mBibleDetail
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            mBibleDetail.value
        )

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
}
