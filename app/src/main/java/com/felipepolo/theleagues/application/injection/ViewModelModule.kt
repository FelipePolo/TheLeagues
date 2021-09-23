package com.felipepolo.pokemonapp.di

import androidx.lifecycle.ViewModelProvider
import com.felipepolo.theleagues.core.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}