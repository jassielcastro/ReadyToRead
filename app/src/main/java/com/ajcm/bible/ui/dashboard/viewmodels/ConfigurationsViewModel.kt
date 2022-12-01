package com.ajcm.bible.ui.dashboard.viewmodels

import androidx.annotation.IntRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajcm.domain.entity.Configuration
import com.ajcm.domain.usecase.configuration.ConfigurationUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ConfigurationsViewModel @Inject constructor(
    private val configurationUc: ConfigurationUc
) : ViewModel() {

    private val mConfigurations = MutableSharedFlow<Configuration?>()
    val configurations = mConfigurations
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null
        )

    fun getConfigurations() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            configurationUc.getConfigurations().collect {
                mConfigurations.emit(it)
            }
        }
    }

    fun updateTextSizeMultiplier(
        @IntRange(from = 1, to = 5) multiplier: Int
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            configurations.value?.copy(textSizeMultiplier = multiplier)?.let {
                configurationUc.updateConfigurations(it)
            }
        }
    }
}
