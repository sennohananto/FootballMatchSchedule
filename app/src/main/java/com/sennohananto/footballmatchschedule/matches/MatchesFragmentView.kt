package com.sennohananto.footballmatchschedule.matches

interface MatchesFragmentView{
    fun loadListLeague(listLeague: List<League>)
    fun showLoading()
    fun hideLoading()
}