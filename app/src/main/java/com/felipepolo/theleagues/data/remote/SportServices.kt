package com.felipepolo.theleagues.data.remote

import com.felipepolo.theleagues.data.model.League
import com.felipepolo.theleagues.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportServices {

    @GET("search_all_teams.php")
    suspend fun getTeamsByLeague(@Query("l") league: String): League

    @GET("eventslast.php?id={id}")
    suspend fun getLastFiveEventsByTeam(@Path("id") id: String): Result

}