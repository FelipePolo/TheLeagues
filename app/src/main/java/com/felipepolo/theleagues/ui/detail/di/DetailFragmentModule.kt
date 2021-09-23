package com.felipepolo.theleagues.ui.detail.di

import androidx.lifecycle.ViewModel
import com.felipepolo.theleagues.application.injection.ViewModelKey
import com.felipepolo.theleagues.presentation.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindHomeViewModel(viewModel: DetailViewModel): ViewModel
}