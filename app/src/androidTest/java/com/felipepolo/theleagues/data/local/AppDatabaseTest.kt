package com.felipepolo.theleagues.data.local

import com.felipepolo.theleagues.DependenciesProvider
import com.felipepolo.theleagues.data.model.TeamEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppDatabaseTest {

    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = DependenciesProvider().appDatabase
    }

    @Test
    fun testIsDatabaseNotOpen() {
        assertThat(database.isOpen).isFalse()
    }

    @Test
    fun testDatabasePathIsMemory() = runBlocking {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.path).isEqualTo(":memory:")
    }

    @Test
    fun testDatabaseVersionIsCurrent() = runBlocking {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.version).isEqualTo(1)
    }

    private fun executeDatabaseFunction() = runBlocking {
        val teamEntity = TeamEntity(idTeam = "1421",strLeague = "Spanish La Liga")
        database.sportDao().saveTeam(teamEntity)
    }

    @After
    fun tearDown() {
        database.close()
    }

}