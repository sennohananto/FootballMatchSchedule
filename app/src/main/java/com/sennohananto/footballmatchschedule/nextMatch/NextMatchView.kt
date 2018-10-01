package com.sennohananto.footballmatchschedule.nextMatch

import com.sennohananto.footballmatchschedule.model.Event

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>?)
}