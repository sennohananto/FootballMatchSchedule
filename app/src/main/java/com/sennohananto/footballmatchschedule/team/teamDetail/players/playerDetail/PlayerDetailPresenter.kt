package com.sennohananto.footballmatchschedule.team.teamDetail.players.playerDetail

import com.sennohananto.footballmatchschedule.team.teamDetail.players.Player

class PlayerDetailPresenter(private val view: PlayerDetailView){
    fun showPlayerInfo(player: Player){
        view.showPlayers(player)
    }
}