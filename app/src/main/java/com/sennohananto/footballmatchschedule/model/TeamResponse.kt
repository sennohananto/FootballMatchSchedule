package com.sennohananto.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class TeamResponse:Serializable {

    @SerializedName("teams")
    @Expose
    var teams: List<Team>? = null
}

class Team:Serializable {
//    val id: Long?,
//    var idTeam: String?,
//    var strTeam: String?,
//    var intFormedYear: String?,
//    var idLeague: String,
//    var strStadium: String?,
//    var strDescriptionEN: String?,
//    var strTeamBadge: String?

    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null
    @SerializedName("strTeam")
    @Expose
    var strTeam: String? = null
    @SerializedName("intFormedYear")
    @Expose
    var intFormedYear: String? = null
    @SerializedName("idLeague")
    @Expose
    var idLeague: String? = null
    @SerializedName("strStadium")
    @Expose
    var strStadium: String? = null
    @SerializedName("strDescriptionEN")
    @Expose
    var strDescriptionEN: String? = null
    @SerializedName("strTeamBadge")
    @Expose
    var strTeamBadge: String? = null

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "table_favorite_teams"
        const val ID: String = "id"
        const val ID_TEAM: String = "idTeam"
        const val STR_TEAM: String = "strTeam"
        const val INT_FORMED_YEAR: String = "intFormedYear"
        const val ID_LEAGUE: String = "idLeague"
        const val STR_STADIUM: String = "strStadium"
        const val STR_DESCRIPTION_EN: String = "strDescriptionEN"
        const val STR_TEAM_BADGE: String = "strTeamBadge"
    }
}