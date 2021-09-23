package com.felipepolo.theleagues.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felipepolo.theleagues.data.model.TeamEntity

@Dao
interface SportDao {
    @Query("SELECT * FROM teamCache WHERE strLeague == :league")
    suspend fun getTeamListByLeague(league: String): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTeam(teamEntity: TeamEntity)
}