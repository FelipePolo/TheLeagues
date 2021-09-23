package com.felipepolo.theleagues.application.injection

import com.felipepolo.theleagues.application.injection.scopes.FragmentScope
import com.felipepolo.theleagues.domain.SportRepositoryImp
import com.felipepolo.theleagues.domain.SportRepositoryInt
import com.felipepolo.theleagues.ui.detail.DetailFragment
import com.felipepolo.theleagues.ui.detail.di.DetailFragmentModule
import com.felipepolo.theleagues.ui.home.HomeFragment
import com.felipepolo.theleagues.ui.home.di.HomeFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindMainFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    abstract fun bindDetailFragment(): DetailFragment

    @Binds
    abstract fun provideSportRepo(sportRepositoryImp: SportRepositoryImp): SportRepositoryInt
}