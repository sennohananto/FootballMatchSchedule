package com.sennohananto.footballmatchschedule.team.teamDetail

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun loadTeamBadge(url:String)
    fun loadTeamName(teamName:String)
    fun loadTeamYear(year:String)
    fun loadTeamStadion(teamStadion:String)
    fun loadOverview(overview: String)
    fun loadPlayers(idTeam: String)

}