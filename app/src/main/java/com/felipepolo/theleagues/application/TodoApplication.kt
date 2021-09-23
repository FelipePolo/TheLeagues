package com.felipepolo.theleagues.application

import com.felipepolo.theleagues.application.injection.AppGraph
import com.felipepolo.theleagues.application.injection.DaggerAppGraph
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TodoApplication: DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppGraph.factory().create(this)
    }

}