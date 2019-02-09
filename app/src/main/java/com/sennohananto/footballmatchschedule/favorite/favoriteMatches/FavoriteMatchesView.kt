package com.sennohananto.footballmatchschedule.favorite.favoriteMatches

import com.sennohananto.footballmatchschedule.database.FavoriteMatch

interface FavoriteMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteMatchesList(data: List<FavoriteMatch>?)
}