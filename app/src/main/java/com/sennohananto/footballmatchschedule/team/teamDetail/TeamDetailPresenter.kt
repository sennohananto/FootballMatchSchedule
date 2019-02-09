package com.sennohananto.footballmatchschedule.team.searchTeam

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.TeamResponse

class SearchTeamPresenter(private val view: SearchTeamView){
    fun querySearch(keyWord:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/searchteams.php")
                .addQueryParameter("t",keyWord)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(TeamResponse::class.java, object : ParsedRequestListener<TeamResponse> {
                    override fun onResponse(response: TeamResponse?) {
                        view.loadSearchResult(response!!)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}