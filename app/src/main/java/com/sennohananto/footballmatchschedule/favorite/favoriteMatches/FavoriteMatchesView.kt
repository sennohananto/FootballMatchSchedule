package com.sennohananto.footballmatchschedule.favorite

import com.sennohananto.footballmatchschedule.database.Favorite

interface FavoriteView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteList(data: List<Favorite>?)
}