package com.sennohananto.footballmatchschedule.team.teamDetail.overview

class OverviewPresenter(private val view: OverviewView){
    fun loadOverview(overview:String){
        view.loadOverview(overview)
    }
}