package com.sennohananto.footballmatchschedule.matches

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Leagues(val listLeague: ) {

    @SerializedName("leagues")
    @Expose
    var leagues: List<League>? = listLeague
}