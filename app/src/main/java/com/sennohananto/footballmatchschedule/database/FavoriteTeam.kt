package com.sennohananto.footballmatchschedule.database

class FavoriteTeam(
        val id: Long?,
        var idTeam: String?,
        var strTeam: String?,
        var intFormedYear: String?,
        var idLeague: String,
        var strStadium: String?,
        var strDescriptionEN: String?,
        var strTeamBadge: String?
){
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