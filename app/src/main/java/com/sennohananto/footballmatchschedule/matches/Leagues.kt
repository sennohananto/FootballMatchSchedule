package com.sennohananto.footballmatchschedule.matches

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Leagues(val listLeague: List<League>) {
    @SerializedName("leagues")
    @Expose
    var leagues: List<League>? = listLeague
}

class League {

    @SerializedName("idLeague")
    @Expose
    var idLeague: String? = null
    @SerializedName("strLeague")
    @Expose
    var strLeague: String? = null
    @SerializedName("strSport")
    @Expose
    var strSport: String? = null
    @SerializedName("strLeagueAlternate")
    @Expose
    var strLeagueAlternate: String? = null

    override fun toString(): String {
        return strLeague!!
    }

}