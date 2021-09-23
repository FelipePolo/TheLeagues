package com.felipepolo.theleagues.data.remote

import com.felipepolo.theleagues.data.model.League
import com.felipepolo.theleagues.data.model.Events
import retrofit2.http.GET
import retrofit2.http.Query

interface SportServices {

    @GET("search_all_teams.php")
    suspend fun getTeamsByLeague(@Query("l") league: String): League

    @GET("eventslast.php")
    suspend fun getLastFiveEventsByTeam(@Query("id") id: String): Events

}