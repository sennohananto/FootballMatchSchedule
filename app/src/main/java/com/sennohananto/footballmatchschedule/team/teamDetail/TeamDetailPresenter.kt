package com.sennohananto.footballmatchschedule.team.teamDetail

import com.sennohananto.footballmatchschedule.model.Team

class TeamDetailPresenter(private val view: TeamDetailView){
    fun loadData(team:Team){
        view.showLoading()
        view.loadTeamBadge(team.strTeamBadge!!)
        view.loadTeamName(team.strTeam!!)
        view.loadTeamYear(team.intFormedYear!!)
        view.loadTeamStadion(team.strStadium!!)
        view.hideLoading()
    }

    fun addToFavorites(team: Team){
        view.addToFavorite(team)
    }



    fun removeFavorites(team: Team){
        view.removeFromFavorite(team)
    }

    fun setFavorite(){
        view.setFavorite()
    }
}