package com.sennohananto.footballmatchschedule.matches

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class MatchesFragmentPresenter(private val view: MatchesFragmentView){
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
}