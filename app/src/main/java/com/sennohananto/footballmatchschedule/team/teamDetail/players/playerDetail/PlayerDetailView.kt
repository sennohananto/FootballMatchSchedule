package com.sennohananto.footballmatchschedule.team.teamDetail.players

interface PlayersView{
    fun showPlayers(players:Players)
    fun showLoading()
    fun hideLoading()
}