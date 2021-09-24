package com.felipepolo.theleagues.data.remote

import com.felipepolo.theleagues.DependenciesProvider
import com.felipepolo.theleagues.core.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NetworkDatSourceTest {

    private lateinit var networkDatSource: NetworkDataSource
    @Before
    fun setUp() {
        networkDatSource = DependenciesProvider().networkDataSource
    }

    @Test
    fun getTeamListByLeagueTest(): Unit = runBlocking {
        when(val result = networkDatSource.getTeamListByLeague("Spanish La Liga")) {
            is Resource.Success -> {
                assertThat(result.data.isEmpty()).isFalse()
                assertThat(result.data.get(0).idTeam).isNotEmpty()
            }
            else -> {}
        }
    }

    @Test
    fun getLastFiveEventsByTeamTest() = runBlocking {
        when(val result = networkDatSource.getLastFiveEventsByTeam("134221")) {
            is Resource.Success -> {
                assertThat(result.data.size).isEqualTo(5)
            }
            else -> {}
        }
    }

}