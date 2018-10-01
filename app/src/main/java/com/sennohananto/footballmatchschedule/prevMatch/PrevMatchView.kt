package com.sennohananto.footballmatchschedule.prevMatch

import com.sennohananto.footballmatchschedule.model.Event

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>?)
}