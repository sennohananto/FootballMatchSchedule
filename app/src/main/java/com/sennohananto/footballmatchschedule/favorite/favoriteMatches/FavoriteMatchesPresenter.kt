package com.sennohananto.footballmatchschedule.favorite.favoriteMatches

import android.content.Context
import com.sennohananto.footballmatchschedule.database.FavoriteMatch
import com.sennohananto.footballmatchschedule.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchesPresenter(private val context:Context, private val view: FavoriteMatchesView){
    fun getFavoriteList(){
        view.showLoading()
        context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            val favoriteMatches: MutableList<FavoriteMatch> = mutableListOf()
            favoriteMatches.addAll(favorite)
            view.showFavoriteMatchesList(favoriteMatches)
        }
        view.hideLoading()
    }
}