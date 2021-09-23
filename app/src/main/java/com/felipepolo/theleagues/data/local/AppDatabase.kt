package com.felipepolo.theleagues.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipepolo.theleagues.data.model.TeamEntity

@Database(entities = [TeamEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sportDao(): SportDao
}