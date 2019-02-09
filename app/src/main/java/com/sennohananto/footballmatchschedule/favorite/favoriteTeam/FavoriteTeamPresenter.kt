package com.sennohananto.footballmatchschedule.favorite.favoriteTeam

import android.content.Context
import com.sennohananto.footballmatchschedule.database.FavoriteTeam
import com.sennohananto.footballmatchschedule.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val context:Context, private val view: FavoriteTeamView){
    fun getFavoriteTeamList(){
        view.showLoading()
        context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            val favoriteTeam: MutableList<FavoriteTeam> = mutableListOf()
            favoriteTeam.addAll(favorite)
            view.showFavoriteTeamList(favoriteTeam)
        }
        view.hideLoading()
    }
}