package com.sennohananto.footballmatchschedule.team.teamDetail

import com.sennohananto.footballmatchschedule.model.Team

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun loadTeamBadge(url:String)
    fun loadTeamName(teamName:String)
    fun loadTeamYear(year:String)
    fun loadTeamStadion(teamStadion:String)
    fun addToFavorite(team: Team)
    fun removeFromFavorite(team: Team)
    fun setFavorite()

}