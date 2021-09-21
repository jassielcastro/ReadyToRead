package com.ajcm.data.usecase

interface BaseListUseCase<R> {
    suspend operator fun invoke() : List<R>
}

interface BaseUseCaseWithParams<R, P> {
    suspend operator fun invoke(vararg params: P) : R
}

interface BaseListUseCaseWithParams<R, P> {
    suspend operator fun invoke(vararg params: P) : List<R>
}
