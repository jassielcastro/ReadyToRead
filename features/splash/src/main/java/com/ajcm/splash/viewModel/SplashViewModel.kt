package com.ajcm.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.domain.usecase.bible.FavouriteBiblesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val favouriteBiblesUC: FavouriteBiblesUC
) : ViewModel() {

    private val mHasFavouriteBibles = MutableStateFlow(false)
    val hasFavouriteBibles = mHasFavouriteBibles.asStateFlow()

    init {
        viewModelScope.launch {
            val hasFavourite = withContext(Dispatchers.IO) {
                favouriteBiblesUC().isNotEmpty()
            }
            mHasFavouriteBibles.value = hasFavourite
        }
    }

}
