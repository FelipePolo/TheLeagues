package com.felipepolo.theleagues.domain

import com.felipepolo.theleagues.core.Resource
import com.felipepolo.theleagues.data.model.Event
import com.felipepolo.theleagues.data.model.TeamEntity

interface SportRepositoryInt {
    suspend fun getTeamListByLeague(league:String): Resource<List<TeamEntity>>
    suspend fun getEventListByTeam(id:String): Resource<List<Event>>
}