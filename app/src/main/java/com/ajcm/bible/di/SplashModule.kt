package com.ajcm.bible.di

import com.ajcm.domain.usecase.bible.FavouriteBiblesUC
import com.ajcm.splash.viewModel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class SplashModule {

    @Provides
    fun provideSplashViewModel(favouriteBiblesUC: FavouriteBiblesUC): SplashViewModel {
        return SplashViewModel(favouriteBiblesUC)
    }

}
