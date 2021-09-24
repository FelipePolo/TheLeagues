package com.felipepolo.theleagues

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.felipepolo.theleagues.application.AppConstants
import com.felipepolo.theleagues.data.local.AppDatabase
import com.felipepolo.theleagues.data.local.LocalDataSource
import com.felipepolo.theleagues.data.local.SportDao
import com.felipepolo.theleagues.data.remote.NetworkDataSource
import com.felipepolo.theleagues.data.remote.SportServices
import com.felipepolo.theleagues.domain.SportRepositoryImp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependenciesProvider {
    val appDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    ).allowMainThreadQueries().build()
    val sportDao = appDatabase.sportDao()
    val retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.SPORT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val sportServices = retrofit.create(SportServices::class.java)
    val networkDataSource = NetworkDataSource(sportServices)
    val localDataSource = LocalDataSource(sportDao)
    val sportRepositoryImp = SportRepositoryImp(localDataSource,networkDataSource)
}