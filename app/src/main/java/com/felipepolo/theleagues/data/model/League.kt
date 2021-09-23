package com.felipepolo.theleagues.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class League(
    var teams: ArrayList<TeamEntity> = arrayListOf()
)

@Entity(tableName = "teamCache")
@Parcelize
data class TeamEntity(
    @ColumnInfo(name = "idTeam")
    @PrimaryKey(autoGenerate = false)
    var idTeam: String,
    @ColumnInfo(name = "strTeam")
    var strTeam: String? = "",
    @ColumnInfo(name = "strStadium")
    var strStadium: String? = "",
    @ColumnInfo(name = "strDescriptionES")
    var strDescriptionES: String? = "",
    @ColumnInfo(name = "strLeague")
    var strLeague: String,
    @ColumnInfo(name = "intFormedYear")
    var intFormedYear: String? = "",
    @ColumnInfo(name = "strTeamBadge")
    var strTeamBadge: String? = "",
    @ColumnInfo(name = "strTeamJersey")
    var strTeamJersey: String? = "",
    @ColumnInfo(name = "strTeamBanner")
    var strTeamBanner: String? = "",
    @ColumnInfo(name = "strWebsite")
    var strWebsite: String? = "",
    @ColumnInfo(name = "strFacebook")
    var strFacebook: String? = "",
    @ColumnInfo(name = "strTwitter")
    var strTwitter: String? = "",
    @ColumnInfo(name = "strInstagram")
    var strInstagram: String? = "",
    @ColumnInfo(name = "strYoutube")
    var strYoutube: String? = ""
) : Parcelable

data class Events(
    var results: ArrayList<Event> = arrayListOf()
)

data class Event(
    var strEvent: String? = ""
)