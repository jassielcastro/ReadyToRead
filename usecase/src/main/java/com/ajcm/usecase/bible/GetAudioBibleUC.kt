package com.ajcm.usecase.bible

import com.ajcm.data.repository.IBibleRepository
import com.ajcm.data.usecase.BaseUseCaseWithParams
import com.ajcm.domain.AudioBible
import javax.inject.Inject

class GetAudioBibleUC @Inject constructor(private val repository: IBibleRepository) :
    BaseUseCaseWithParams<AudioBible, String> {
    override suspend fun invoke(vararg params: String): AudioBible {
        return repository.getAudioBible(params[0])
    }
}
