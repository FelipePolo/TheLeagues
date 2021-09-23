package com.felipepolo.theleagues.application.injection

import android.content.Context
import com.felipepolo.theleagues.application.AppConstants.SPORT_BASE_URL
import com.felipepolo.theleagues.application.TodoApplication
import com.felipepolo.theleagues.data.remote.SportServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(app: TodoApplication): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SPORT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSportWebService(retrofit: Retrofit): SportServices {
        return retrofit.create(SportServices::class.java)
    }

}