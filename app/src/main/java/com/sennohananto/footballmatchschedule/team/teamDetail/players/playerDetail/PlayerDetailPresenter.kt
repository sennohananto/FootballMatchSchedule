package com.sennohananto.footballmatchschedule.team.teamDetail.players

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class PlayerDetailPresenter(private val view: PlayersView){
    fun loadPlayers(idTeam:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php")
                .setPriority(Priority.HIGH)
                .addQueryParameter("id",idTeam)
                .build()
                .getAsObject(Players::class.java, object : ParsedRequestListener<Players> {
                    override fun onResponse(response: Players?) {
                        view.showPlayers(response!!)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}