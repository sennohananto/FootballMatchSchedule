package com.sennohananto.footballmatchschedule.matches.prevMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.Match

class PrevMatchPresenter(private val view: PrevMatchView){
    fun getTeamList(idLeague:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?")
                .addQueryParameter("id",idLeague)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
                    override fun onResponse(response: Match?) {
                        view.showPrevMatchList(response?.events)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}