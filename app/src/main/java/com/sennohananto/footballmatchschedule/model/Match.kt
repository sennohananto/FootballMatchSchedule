package com.sennohananto.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Match(val listEvent:List<Event>):Serializable{
    @SerializedName("events")
    @Expose
    var events: List<Event>? = null
}


class Event:Serializable{

    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null
    @SerializedName("strEvent")
    @Expose
    var strEvent: String? = null
    @SerializedName("strHomeTeam")
    @Expose
    var strHomeTeam: String? = null
    @SerializedName("strAwayTeam")
    @Expose
    var strAwayTeam: String? = null
    @SerializedName("intHomeScore")
    @Expose
    var intHomeScore: Any? = null
    @SerializedName("intAwayScore")
    @Expose
    var intAwayScore: Any? = null
    @SerializedName("strHomeGoalDetails")
    @Expose
    var strHomeGoalDetails: Any? = null
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    var strHomeLineupGoalkeeper: Any? = null
    @SerializedName("strHomeLineupDefense")
    @Expose
    var strHomeLineupDefense: Any? = null
    @SerializedName("strHomeLineupMidfield")
    @Expose
    var strHomeLineupMidfield: Any? = null
    @SerializedName("strHomeLineupForward")
    @Expose
    var strHomeLineupForward: Any? = null
    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    var strHomeLineupSubstitutes: Any? = null
    @SerializedName("strHomeFormation")
    @Expose
    var strHomeFormation: Any? = null
    @SerializedName("strAwayGoalDetails")
    @Expose
    var strAwayGoalDetails: Any? = null
    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    var strAwayLineupGoalkeeper: Any? = null
    @SerializedName("strAwayLineupDefense")
    @Expose
    var strAwayLineupDefense: Any? = null
    @SerializedName("strAwayLineupMidfield")
    @Expose
    var strAwayLineupMidfield: Any? = null
    @SerializedName("strAwayLineupForward")
    @Expose
    var strAwayLineupForward: Any? = null
    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    var strAwayLineupSubstitutes: Any? = null
    @SerializedName("strAwayFormation")
    @Expose
    var strAwayFormation: Any? = null
    @SerializedName("intHomeShots")
    @Expose
    var intHomeShots: Any? = null
    @SerializedName("intAwayShots")
    @Expose
    var intAwayShots: Any? = null
    @SerializedName("strDate")
    @Expose
    var strDate: String? = null
    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String? = null
    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String? = null

}