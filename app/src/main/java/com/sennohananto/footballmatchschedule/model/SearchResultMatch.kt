package com.sennohananto.footballmatchschedule.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchResultMatch:Serializable{
    @SerializedName("event")
    @Expose
    var event: List<Event>? = null
}