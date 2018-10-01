package com.sennohananto.footballmatchschedule.detailMatch

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.model.Match
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchActivityTest {
    @Mock
    private
    lateinit var view:DetailMatchView

    private lateinit var presenter: DetailMatchPresenter


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view)
    }

    @Test
    fun showEventData() {
        val idEvent = "576514"
        val events:MutableList<Event> = mutableListOf()
        val match = Match(events)
        Mockito.`when`(
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupevent.php")
        .setPriority(Priority.HIGH)
        .addQueryParameter("id",idEvent)
        .build()
        .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
        override fun onResponse(response: Match?) {

        }

        override fun onError(anError: ANError?) {

        }

    })

        )
    }

//    AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupevent.php")
//    .setPriority(Priority.HIGH)
//    .addQueryParameter("id",idEvent)
//    .build()
//    .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
//        override fun onResponse(response: Match?) {
//
//            event = response?.events?.get(0)!!
//
//            supportActionBar?.title = event.strEvent
//            tvDateMatch.text = toDateIndo(event.strDate!!,"dd/MM/yy")
//
//            tvSubstitutesHome.text = event.strHomeLineupSubstitutes?.toString()?.replace("; ","\n")?.replace(";","")
//            tvForwardHome.text = event.strHomeLineupForward?.toString()?.replace("; ","\n")?.replace(";","")
//            tvMidfieldHome.text = event.strHomeLineupMidfield?.toString()?.replace("; ","\n")?.replace(";","")
//            tvDefenseHome.text = event.strHomeLineupDefense?.toString()?.replace("; ","\n")?.replace(";","")
//            tvGoalHome.text = event.strHomeLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")
//            tvShotsHome.text = event.intHomeShots?.toString()
//            tvGoalHome.text = event.strHomeGoalDetails?.toString()?.replace("; ","\n")?.replace(";","")
//            tvTeamHome.text = event.strHomeTeam
//            tvScoreHome.text = event.intHomeScore?.toString()
//            tvFormationHome.text = event.strHomeFormation?.toString()
//            tvKeeperHome.text = event.strHomeLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")
//
//            tvSubstitutesAway.text = event.strAwayLineupSubstitutes?.toString()?.replace("; ","\n")?.replace(";","")
//            tvForwardAway.text = event.strAwayLineupForward?.toString()?.replace("; ","\n")?.replace(";","")
//            tvMidfieldAway.text = event.strAwayLineupMidfield?.toString()?.replace("; ","\n")?.replace(";","")
//            tvDefenseAway.text = event.strAwayLineupDefense?.toString()?.replace("; ","\n")?.replace(";","")
//            tvGoalAway.text = event.strAwayLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")
//            tvShotsAway.text = event.intAwayShots?.toString()
//            tvGoalAway.text = event.strAwayGoalDetails?.toString()?.replace("; ","\n")?.replace(";","")
//            tvTeamAway.text = event.strAwayTeam
//            tvScoreAway.text = event.intAwayScore?.toString()
//            tvFormationAway.text = event.strAwayFormation?.toString()
//            tvKeeperAway.text = event.strAwayLineupGoalkeeper?.toString()
//
//            swipeRefresh.setOnRefreshListener {
//                swipeRefresh.isRefreshing = true
//                presenter.showEventData(event.idEvent.toString())
//            }
//
//            presenter.getTeamDetail(event.idAwayTeam!!,teamBadgeAway)
//            presenter.getTeamDetail(event.idHomeTeam!!,teamBadgeHome)
//
//
//            presenter.checkFavorites(event)
//            presenter.setFavorite(event)
//
//            swipeRefresh.isRefreshing = false
//        }
//
//        override fun onError(anError: ANError?) {
//            swipeRefresh.isRefreshing = false
//        }
//
//    })
}