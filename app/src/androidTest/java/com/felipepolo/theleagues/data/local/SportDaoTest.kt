package com.felipepolo.theleagues.data.local

import com.felipepolo.theleagues.DependenciesProvider
import com.felipepolo.theleagues.data.model.TeamEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SportDaoTest {
    private lateinit var dao: SportDao

    @Before
    fun setUp() {
        dao = DependenciesProvider().sportDao
    }

    @Test
    fun saveTeamTest() = runBlocking {
        val team = getExampleTeam()
        dao.saveTeam(team)
        val teamList = dao.getTeamListByLeague("Spanish La Liga")
        assertThat(teamList.contains(team)).isEqualTo(true)
    }

    @Test
    fun replaceEqualsTest() = runBlocking {
        val team1 = getExampleTeam()
        val team2 = getExampleTeam()
        dao.saveTeam(team1)
        dao.saveTeam(team2)
        val teamList = dao.getTeamListByLeague("Spanish La Liga")
        assertThat(teamList.size).isEqualTo(1)
    }

    @Test
    fun getTeamListByLeagueTest() = runBlocking {
        val team = getExampleTeam()
        val team2 = getExampleTeam()
        dao.saveTeam(team)
        dao.saveTeam(team2)
        val teamList = dao.getTeamListByLeague("Spanish La Liga")
        assertThat(teamList.isEmpty()).isFalse()
    }

    private fun getExampleTeam() : TeamEntity {
        return TeamEntity(idTeam = "1432", strLeague = "Spanish La Liga")
    }
}