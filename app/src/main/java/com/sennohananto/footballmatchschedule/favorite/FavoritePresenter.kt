package com.sennohananto.footballmatchschedule.favorite.favoriteMatches

import android.content.Context
import com.sennohananto.footballmatchschedule.database.Favorite
import com.sennohananto.footballmatchschedule.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchesPresenter(private val context:Context, private val view: FavoriteMatchesView){
    fun getFavoriteList(){
        view.showLoading()
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            val favorites: MutableList<Favorite> = mutableListOf()
            favorites.addAll(favorite)
            view.showFavoriteMatchesList(favorites)
        }
        view.hideLoading()
    }
}