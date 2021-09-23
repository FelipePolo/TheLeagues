package com.felipepolo.theleagues.application.injection

import com.felipepolo.theleagues.application.TodoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentBuilderModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppGraph: AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: TodoApplication): AppGraph
    }
}