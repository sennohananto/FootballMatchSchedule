package com.sennohananto.footballmatchschedule.matches

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sennohananto.footballmatchschedule.matches.nextMatch.NextMatchFragment
import com.sennohananto.footballmatchschedule.matches.prevMatch.PrevMatchFragment


class MatchesPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 2

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> PrevMatchFragment()
            1 -> NextMatchFragment()
            else -> null
        }
    }
}