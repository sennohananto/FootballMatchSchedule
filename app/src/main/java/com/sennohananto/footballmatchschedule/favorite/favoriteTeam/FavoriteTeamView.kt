package com.sennohananto.footballmatchschedule.favorite.favoriteTeam

import com.sennohananto.footballmatchschedule.database.FavoriteTeam

interface FavoriteTeamView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteTeamList(data: List<FavoriteTeam>?)
}