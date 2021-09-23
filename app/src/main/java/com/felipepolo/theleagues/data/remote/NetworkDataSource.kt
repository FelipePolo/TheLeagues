package com.felipepolo.theleagues.data.remote

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.Event
import com.felipepolo.theleagues.data.model.TeamEntity
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val sportServices: SportServices) {

    suspend fun getTeamListByLeague(league: String): Resource<ArrayList<TeamEntity>> {
        return Resource.Success(sportServices.getTeamsByLeague(league).teams)
    }

    suspend fun getLastFiveEventsByTeam(teamId: String): Resource<List<Event>> {
        return Resource.Success(sportServices.getLastFiveEventsByTeam(teamId).results)
    }

}