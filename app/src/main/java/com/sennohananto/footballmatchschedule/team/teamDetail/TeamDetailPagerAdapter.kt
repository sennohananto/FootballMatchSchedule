package com.sennohananto.footballmatchschedule.team.teamDetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sennohananto.footballmatchschedule.team.teamDetail.overview.OverviewFragment
import com.sennohananto.footballmatchschedule.team.teamDetail.players.PlayersFragment


class TeamDetailPagerAdapter(fragmentManager: FragmentManager, val overviewFragment: OverviewFragment, val playersFragment: PlayersFragment) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 2

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> overviewFragment
            1 -> playersFragment
            else -> null
        }
    }
}