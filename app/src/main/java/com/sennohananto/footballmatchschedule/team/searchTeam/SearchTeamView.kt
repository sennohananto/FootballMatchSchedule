package com.sennohananto.footballmatchschedule.matches.searchMatch

import com.sennohananto.footballmatchschedule.model.SearchResultMatch

interface SearchMatchView{
    fun querySearch(keyword: String)
    fun loadSearchResult(result:SearchResultMatch)
    fun showLoading()
    fun hideLoading()
}