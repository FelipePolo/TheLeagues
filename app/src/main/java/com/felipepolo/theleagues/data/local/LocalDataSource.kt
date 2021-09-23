package com.felipepolo.theleagues.data.local

import com.felipepolo.theleagues.data.model.TeamEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sportDao: SportDao) {
    suspend fun getTeamListByLeague(league: String): List<TeamEntity> {
        return sportDao.getTeamListByLeague(league)
    }

    suspend fun saveTeamList(data: List<TeamEntity>) {
        data.forEach { sportDao.saveTeam(it) }
    }
}