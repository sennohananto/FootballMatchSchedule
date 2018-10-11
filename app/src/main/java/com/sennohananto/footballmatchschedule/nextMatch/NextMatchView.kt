package com.sennohananto.footballmatchschedule.nextMatch

import com.sennohananto.footballmatchschedule.model.Event

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<Event>?)
}