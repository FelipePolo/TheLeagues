package com.felipepolo.theleagues.domain

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.Result
import com.felipepolo.theleagues.data.model.Team

interface SportRepositoryInt {
    suspend fun getTeamListByLeague(league:String): Resource<List<Team>>
    suspend fun getEventListByTeam(id:String): Resource<Result>
}