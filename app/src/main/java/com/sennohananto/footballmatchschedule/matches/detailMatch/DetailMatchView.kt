package com.sennohananto.footballmatchschedule.matches.detailMatch

import android.widget.ImageView
import com.sennohananto.footballmatchschedule.model.Event

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun setTeamBadge(urlBadge: String, img:ImageView)
    fun showEventData(idEvent:String)
    fun addToFavorite(event: Event)
    fun removeFromFavorite(event: Event)
    fun checkFavorite(event: Event)
    fun setFavorite(event: Event)
}