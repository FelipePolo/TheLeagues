package com.felipepolo.theleagues.domain

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.local.LocalDataSource
import com.felipepolo.theleagues.data.model.Event
import com.felipepolo.theleagues.data.remote.NetworkDataSource
import com.felipepolo.theleagues.data.model.TeamEntity
import javax.inject.Inject

class SportRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
    ):SportRepositoryInt {

    override suspend fun getTeamListByLeague(league: String): Resource<List<TeamEntity>> {
        val data = localDataSource.getTeamListByLeague(league)
        return if(data.isEmpty()){
            val fetch = networkDataSource.getTeamListByLeague(league)
            saveTeamList(fetch)
            return fetch
        }else{
            Resource.Success(data)
        }
    }

    override suspend fun getEventListByTeam(id: String): Resource<List<Event>> {
        return networkDataSource.getLastFiveEventsByTeam(id)
    }

    private suspend fun saveTeamList(request: Resource<ArrayList<TeamEntity>>) {
        when(request) {
            is Resource.Failure -> {}
            is Resource.Success -> {localDataSource.saveTeamList(request.data)}
            else -> {}
        }
    }
}