package com.sennohananto.footballmatchschedule.matches.searchMatch

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.SearchResultMatch

class SearchMatchPresenter(private val view: SearchMatchView){
    fun querySearch(keyWord:String){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/searchevents.php")
                .addQueryParameter("e",keyWord)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(SearchResultMatch::class.java, object : ParsedRequestListener<SearchResultMatch> {
                    override fun onResponse(response: SearchResultMatch?) {
                        if(response?.event?.size!!>0){
                            view.loadSearchResult(response!!)
                        }else{
                            Log.d("API", "Response NULL")
                        }

                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }
}