package com.sennohananto.footballmatchschedule.team

import com.sennohananto.footballmatchschedule.matches.League
import com.sennohananto.footballmatchschedule.model.TeamResponse

interface TeamFragmentView{
    fun loadListLeague(listLeague: List<League>)
    fun showTeamList(teamResponse: TeamResponse)
    fun showLoading()
    fun hideLoading()
}