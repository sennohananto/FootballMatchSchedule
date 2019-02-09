package com.sennohananto.footballmatchschedule.matches.prevMatch

import com.sennohananto.footballmatchschedule.model.Event

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPrevMatchList(data: List<Event>?)
}