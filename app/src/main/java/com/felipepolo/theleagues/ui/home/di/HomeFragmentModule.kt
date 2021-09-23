package com.felipepolo.theleagues.ui.home.di

import androidx.lifecycle.ViewModel
import com.felipepolo.theleagues.application.injection.ViewModelKey
import com.felipepolo.theleagues.presentation.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}