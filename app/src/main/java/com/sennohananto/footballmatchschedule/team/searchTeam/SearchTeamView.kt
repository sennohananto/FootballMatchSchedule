package com.sennohananto.footballmatchschedule.team.searchTeam

import com.sennohananto.footballmatchschedule.model.TeamResponse

interface SearchTeamView{
    fun querySearch(keyword: String)
    fun loadSearchResult(result:TeamResponse)
    fun showLoading()
    fun hideLoading()
}