package com.sennohananto.footballmatchschedule.team

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.matches.Leagues
import com.sennohananto.footballmatchschedule.model.TeamResponse

class TeamFragmentPresenter(private val view: TeamFragmentView){
    fun getAllLeague(){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/all_leagues.php")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(Leagues::class.java, object : ParsedRequestListener<Leagues> {
                    override fun onResponse(response: Leagues?) {
                        val filteredResponse = response?.leagues?.filter {
                            it.strSport.equals("Soccer")
                        }
                        view.loadListLeague(filteredResponse!!)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }

    fun getTeamList(idLeague:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php")
                .setPriority(Priority.HIGH)
                .addQueryParameter("id",idLeague)
                .build()
                .getAsObject(TeamResponse::class.java, object : ParsedRequestListener<TeamResponse> {
                    override fun onResponse(response: TeamResponse?) {
                        view.showTeamList(response!!)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}