package com.sennohananto.footballmatchschedule.matches.detailMatch

import android.widget.ImageView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.sennohananto.footballmatchschedule.model.Event
import org.json.JSONObject

class DetailMatchPresenter(private val view: DetailMatchView){
    fun getTeamDetail(idTeam:String, img:ImageView){
        view.showLoading()
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php")
                .addQueryParameter("id",idTeam)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        view.setTeamBadge(
                                response?.getJSONArray("teams")?.getJSONObject(0)!!
                                        .getString("strTeamBadge"),img)
                        view.hideLoading()
                    }

                    override fun onError(anError: ANError?) {
                        view.hideLoading()
                    }
                })
    }

    fun showEventData(idEvent : String){
        view.showEventData(idEvent)
    }
    fun addToFavorites(event: Event){
        view.addToFavorite(event)
    }

    fun checkFavorites(event: Event){
        view.checkFavorite(event)
    }

    fun removeFavorites(event: Event){
        view.removeFromFavorite(event)
    }

    fun setFavorite(event: Event){
        view.setFavorite(event)
    }

}