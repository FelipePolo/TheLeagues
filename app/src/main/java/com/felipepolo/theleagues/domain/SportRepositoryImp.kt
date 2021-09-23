package com.felipepolo.theleagues.domain

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.NetworkDataSource
import com.felipepolo.theleagues.data.model.Result
import com.felipepolo.theleagues.data.model.Team
import javax.inject.Inject

class SportRepositoryImp @Inject constructor(private val networkDataSource: NetworkDataSource): SportRepositoryInt {
    override suspend fun getTeamListByLeague(league: String): Resource<List<Team>> {
        return networkDataSource.getTeamListByLeague(league)
    }

    override suspend fun getEventListByTeam(id: String): Resource<Result> {
        TODO("Not yet implemented")
    }

}