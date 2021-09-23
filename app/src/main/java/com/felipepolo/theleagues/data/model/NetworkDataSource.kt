package com.felipepolo.theleagues.data.model

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.remote.SportServices
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val sportServices: SportServices) {

    suspend fun getTeamListByLeague(league:String): Resource<List<Team>> {
        return Resource.Success(sportServices.getTeamsByLeague(league).teams)
    }

    suspend fun getLastFiveEventsByTeam(teamId:String): Resource<List<Event>> {
        return Resource.Success(sportServices.getLastFiveEventsByTeam(teamId).events)
    }

}