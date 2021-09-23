package com.felipepolo.theleagues.application.injection

import com.felipepolo.theleagues.application.injection.scopes.FragmentScope
import com.felipepolo.theleagues.ui.detail.DetailFragment
import com.felipepolo.theleagues.ui.home.HomeFragment
import com.felipepolo.theleagues.ui.home.di.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindMainFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailFragment

}