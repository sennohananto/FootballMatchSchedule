package com.sennohananto.footballmatchschedule.prevMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.Match

class PrevMatchPresenter(private val view: PrevMatchView){
    fun getTeamList(url:String){
        view.showLoading()
        AndroidNetworking.get(url)
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