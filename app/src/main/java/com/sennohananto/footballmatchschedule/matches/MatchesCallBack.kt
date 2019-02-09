package com.sennohananto.footballmatchschedule.matches

interface PrevMatchCallback{
    fun loadPrevMatches(idLeague:String)
}

interface NextMatchCallback{
    fun loadNextMatches(idLeague: String)
}