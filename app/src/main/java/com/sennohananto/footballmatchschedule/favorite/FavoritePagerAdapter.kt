package com.sennohananto.footballmatchschedule.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sennohananto.footballmatchschedule.favorite.favoriteMatches.FavoriteMatchesFragment
import com.sennohananto.footballmatchschedule.favorite.favoriteTeam.FavoriteTeamFragment


class FavoritePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 2

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FavoriteMatchesFragment()
            1 -> FavoriteTeamFragment()
            else -> null
        }
    }
}