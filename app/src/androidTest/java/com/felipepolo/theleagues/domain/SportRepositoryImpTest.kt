package com.felipepolo.theleagues.domain

import com.felipepolo.theleagues.DependenciesProvider
import com.felipepolo.theleagues.core.Resource
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before

class SportRepositoryImpTest : TestCase() {

    private val dependencieProvider = DependenciesProvider()
    private lateinit var sportRepositoryInt: SportRepositoryInt

    @Before
    override fun setUp() {
        sportRepositoryInt = dependencieProvider.sportRepositoryImp
    }

    fun testGetTeamListByLeague() = runBlocking {
        val result = sportRepositoryInt.getTeamListByLeague("Spanish La Liga")
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(Resource.Success(dependencieProvider.localDataSource.getTeamListByLeague("Spanish La Liga")))
    }

    fun testGetEventListByTeam() = runBlocking {
        val result = sportRepositoryInt.getEventListByTeam("134221")
        assertThat(result).isNotNull()
        when(result) {
            is Resource.Success -> {
                assertThat(result.data.size).isEqualTo(5)
                assertThat(result.data[0].strEvent).isNotEmpty()
            }
            else -> {}
        }
    }
}