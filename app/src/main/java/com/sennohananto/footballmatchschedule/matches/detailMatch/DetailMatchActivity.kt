package com.sennohananto.footballmatchschedule.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bumptech.glide.Glide
import com.sennohananto.footballmatchschedule.*
import com.sennohananto.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.sennohananto.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.sennohananto.footballmatchschedule.R.id.add_to_favorite
import com.sennohananto.footballmatchschedule.R.menu.detail_menu
import com.sennohananto.footballmatchschedule.database.FavoriteMatch
import com.sennohananto.footballmatchschedule.database.database
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.model.Match
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    private var menuItem: Menu? = null

    private var isFavorite: Boolean = false
    lateinit var presenter: DetailMatchPresenter
    lateinit var event: Event


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        database.onCreate(database.writableDatabase)

        presenter = DetailMatchPresenter(this)
        presenter.showEventData(intent.getStringExtra("idEvent"))
    }

    override fun showEventData(idEvent: String) {
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupevent.php")
                .setPriority(Priority.HIGH)
                .addQueryParameter("id",idEvent)
                .build()
                .getAsObject(Match::class.java, object : ParsedRequestListener<Match> {
                    override fun onResponse(response: Match?) {

                        event = response?.events?.get(0)!!

                        supportActionBar?.title = event.strEvent
//                        tvDateMatch.text = toDateIndo(event.strDate!!,"dd/MM/yy")
                        tvDateMatch.text = toDateIndo(event.dateEvent!!,"yyyy-MM-dd")
                        tvHourMatch.text = toHourIndo(event.strTime!!)

                        tvSubstitutesHome.text = event.strHomeLineupSubstitutes?.toString()?.replace("; ","\n")?.replace(";","")
                        tvForwardHome.text = event.strHomeLineupForward?.toString()?.replace("; ","\n")?.replace(";","")
                        tvMidfieldHome.text = event.strHomeLineupMidfield?.toString()?.replace("; ","\n")?.replace(";","")
                        tvDefenseHome.text = event.strHomeLineupDefense?.toString()?.replace("; ","\n")?.replace(";","")
                        tvGoalHome.text = event.strHomeLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")
                        tvShotsHome.text = event.intHomeShots?.toString()
                        tvGoalHome.text = event.strHomeGoalDetails?.toString()?.replace("; ","\n")?.replace(";","")
                        tvTeamHome.text = event.strHomeTeam
                        tvScoreHome.text = event.intHomeScore?.toString()
                        tvFormationHome.text = event.strHomeFormation?.toString()
                        tvKeeperHome.text = event.strHomeLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")

                        tvSubstitutesAway.text = event.strAwayLineupSubstitutes?.toString()?.replace("; ","\n")?.replace(";","")
                        tvForwardAway.text = event.strAwayLineupForward?.toString()?.replace("; ","\n")?.replace(";","")
                        tvMidfieldAway.text = event.strAwayLineupMidfield?.toString()?.replace("; ","\n")?.replace(";","")
                        tvDefenseAway.text = event.strAwayLineupDefense?.toString()?.replace("; ","\n")?.replace(";","")
                        tvGoalAway.text = event.strAwayLineupGoalkeeper?.toString()?.replace("; ","\n")?.replace(";","")
                        tvShotsAway.text = event.intAwayShots?.toString()
                        tvGoalAway.text = event.strAwayGoalDetails?.toString()?.replace("; ","\n")?.replace(";","")
                        tvTeamAway.text = event.strAwayTeam
                        tvScoreAway.text = event.intAwayScore?.toString()
                        tvFormationAway.text = event.strAwayFormation?.toString()
                        tvKeeperAway.text = event.strAwayLineupGoalkeeper?.toString()

                        swipeRefresh.setOnRefreshListener {
                            swipeRefresh.isRefreshing = true
                            presenter.showEventData(event.idEvent.toString())
                        }

                        presenter.getTeamDetail(event.idAwayTeam!!,teamBadgeAway)
                        presenter.getTeamDetail(event.idHomeTeam!!,teamBadgeHome)


                        presenter.checkFavorites(event)
                        presenter.setFavorite(event)

                        swipeRefresh.isRefreshing = false
                    }

                    override fun onError(anError: ANError?) {
                        swipeRefresh.isRefreshing = false
                    }

                })
    }

    override fun checkFavorite(event: Event) {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(eventId = {eventId})",
                            FavoriteMatch.EVENT_ID to event.idEvent.toString())
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun setFavorite(event: Event) {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        } else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
        }
    }

    override fun addToFavorite(event: Event){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.ID to null,
                        FavoriteMatch.EVENT_ID to event.idEvent,
                        FavoriteMatch.AWAY_SCORE to event.intAwayScore.toString(),
                        FavoriteMatch.DATE_MATCH to event.dateEvent,
                        FavoriteMatch.HOME_NAME to event.strHomeTeam,
                        FavoriteMatch.HOME_SCORE to event.intHomeScore.toString(),
                        FavoriteMatch.AWAY_NAME to event.strAwayTeam
                )
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }




    override fun removeFromFavorite(event: Event) = try {
        database.use {
            delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH, "(eventId = {eventId})",FavoriteMatch.EVENT_ID to event.idEvent.toString()
            )
        }
        snackbar(swipeRefresh, "Removed to favorite").show()
    } catch (e: SQLiteConstraintException){
        snackbar(swipeRefresh, e.localizedMessage).show()
    }

    override fun setTeamBadge(urlBadge: String, img: ImageView) {
        Glide.with(this).load(urlBadge).into(img)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if(isFavorite){
                    presenter.removeFavorites(event)
                }else{
                    presenter.addToFavorites(event)
                }
                isFavorite = !isFavorite
                presenter.setFavorite(event)
                presenter.checkFavorites(event)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
}
