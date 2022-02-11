package com.ajcm.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.design.common.State
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

    private val mHasFavouriteBibles = MutableStateFlow<State>(State.Loading)
    val hasFavouriteBibles = mHasFavouriteBibles.asStateFlow()

    init {
        println("SplashViewModel. ---> init")
        viewModelScope.launch {
            val hasFavourite = withContext(Dispatchers.IO) {
                favouriteBiblesUC()
            }
            mHasFavouriteBibles.value = if (hasFavourite.isNotEmpty()) {
                State.Success(hasFavourite)
            } else {
                State.Empty
            }
        }
    }

}
