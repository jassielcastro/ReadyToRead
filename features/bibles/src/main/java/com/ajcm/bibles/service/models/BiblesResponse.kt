package com.ajcm.bibles.service.models

import com.ajcm.domain.AudioBible
import com.ajcm.domain.Bible
import com.ajcm.domain.BibleSummary

data class BiblesResponse(
    val data: List<BibleSummary>
)

data class BibleResponse(
    val data: Bible
)

data class AudioBibleResponse(
    val data: AudioBible
)
