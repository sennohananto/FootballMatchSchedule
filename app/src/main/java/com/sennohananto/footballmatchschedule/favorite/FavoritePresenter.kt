package com.sennohananto.footballmatchschedule.favorite

class FavoritePresenter(private val view: FavoriteView){

    fun setUpPagerAdapter(){
        view.setUpPagerAdapter()
    }

}