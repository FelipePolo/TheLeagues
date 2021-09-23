package com.felipepolo.theleagues.data.model

data class League(
    var teams: ArrayList<Team> = arrayListOf()
)


/*  TEAM NAME
    TEAM DESCRIPCION
    TEAM FOUNDATION YEAR
    TEAM BADGE AND JERSEY
    NEXT 5 EVENTS OF THE TEAM
    WEBPAGE AND SOCIAL MEDIA IF ANY
*/

data class Team(
    var idTeam: String? = "",
    var strTeam: String? = "",
    var strStadium: String? = "",
    var strDescriptionES: String? = "",
    var intFormedYear: String? = "",
    var strTeamBadge: String? = "",
    var strTeamJersey: String? = "",
    var strTeamBanner: String? = "",
    var strwebsite: String? = "",
    var strFacebook: String? = "",
    var strTwitter: String? = "",
    var strInstagram: String? = "",
    var strYoutube: String? = ""
)

data class Result(
    var events: ArrayList<Event> = arrayListOf()
)

data class Event(
    var strEvent: String? = ""
)