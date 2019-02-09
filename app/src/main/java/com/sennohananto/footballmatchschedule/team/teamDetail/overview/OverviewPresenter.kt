package com.sennohananto.footballmatchschedule.team.teamDetail

import com.sennohananto.footballmatchschedule.model.Team

class TeamDetailPresenter(private val view: TeamDetailView){
    fun loadData(team:Team){
        view.showLoading()
        view.loadOverview(team.strDescriptionEN!!)
        view.loadPlayers(team.idTeam!!)
        view.loadTeamBadge(team.strTeamBadge!!)
        view.loadTeamName(team.strTeam!!)
        view.loadTeamYear(team.intFormedYear!!)
        view.loadTeamStadion(team.strStadium!!)
        view.hideLoading()
    }
}