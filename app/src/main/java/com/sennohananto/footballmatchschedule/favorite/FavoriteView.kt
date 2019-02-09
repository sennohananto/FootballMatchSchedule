package com.sennohananto.footballmatchschedule.favorite.favoriteMatches

import com.sennohananto.footballmatchschedule.database.Favorite

interface FavoriteMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteMatchesList(data: List<Favorite>?)
}