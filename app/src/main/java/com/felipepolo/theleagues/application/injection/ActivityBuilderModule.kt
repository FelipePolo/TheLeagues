package com.felipepolo.theleagues.application.injection

import com.felipepolo.theleagues.application.MainActivity
import com.felipepolo.theleagues.application.injection.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity

}