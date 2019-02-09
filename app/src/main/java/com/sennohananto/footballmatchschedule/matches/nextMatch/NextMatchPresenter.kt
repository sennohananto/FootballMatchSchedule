package com.sennohananto.footballmatchschedule.matches.nextMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.Match

class NextMatchPresenter(private val view: NextMatchView){
    fun getTeamList(idLeague:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?")
                .setPriority(Priority.HIGH)
                .addQueryParameter("id",idLeague)
                .build()
                .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
                    override fun onResponse(response: Match?) {
                        view.showNextMatchList(response?.events)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}