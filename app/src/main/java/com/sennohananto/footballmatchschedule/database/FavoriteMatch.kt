package com.sennohananto.footballmatchschedule.database

class FavoriteMatch(val id: Long?,
                    val eventId: String?,
                    val awayScore: String?,
                    val dateMatch: String?,
                    val homeName: String?,
                    val homeScore: String?,
                    val awayName: String?,
                    val strTime: String?
) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "table_favorite_matches"
        const val ID: String = "id"
        const val EVENT_ID: String = "eventId"
        const val AWAY_SCORE: String = "awayScore"
        const val DATE_MATCH: String = "dateMatch"
        const val HOME_NAME: String = "homeName"
        const val HOME_SCORE: String = "homeScore"
        const val AWAY_NAME: String = "awayName"
        const val STR_TIME: String = "strTime"
    }
}