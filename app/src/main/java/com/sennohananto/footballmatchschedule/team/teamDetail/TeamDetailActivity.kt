package com.sennohananto.footballmatchschedule.team.teamDetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.database.FavoriteTeam
import com.sennohananto.footballmatchschedule.database.database
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.model.Team
import com.sennohananto.footballmatchschedule.showProgressDialog
import com.sennohananto.footballmatchschedule.team.teamDetail.overview.OverviewFragment
import com.sennohananto.footballmatchschedule.team.teamDetail.players.PlayersFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(),TeamDetailView {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    lateinit var overviewFragment: OverviewFragment
    lateinit var playersFragment: PlayersFragment
    lateinit var team: Team


    override fun addToFavorite(team: Team) {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.ID to null,
                        FavoriteTeam.ID_TEAM to team.idTeam,
                        FavoriteTeam.STR_TEAM to team.strTeam,
                        FavoriteTeam.INT_FORMED_YEAR to team.intFormedYear,
                        FavoriteTeam.ID_LEAGUE to team.idLeague,
                        FavoriteTeam.STR_STADIUM to team.strStadium,
                        FavoriteTeam.STR_DESCRIPTION_EN to team.strDescriptionEN,
                        FavoriteTeam.STR_TEAM_BADGE to team.strTeamBadge
                )
            }
            snackbar(rootViewTeamDetail, "Added to team favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(rootViewTeamDetail, e.localizedMessage).show()
        }
    }

    override fun removeFromFavorite(team: Team) = try {
        database.use {
            delete(
                    FavoriteTeam.TABLE_FAVORITE_TEAM, "(idTeam = {idTeam})",FavoriteTeam.ID_TEAM to team.idTeam.toString()
            )
        }
        snackbar(rootViewTeamDetail, "Removed from favorite teams").show()
    } catch (e: SQLiteConstraintException){
        snackbar(rootViewTeamDetail, e.localizedMessage).show()
    }

    override fun setFavorite() {

        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(idTeam = {idTeam})",
                            FavoriteTeam.ID_TEAM to team.idTeam.toString())
            val favorite = result.parseList(classParser<FavoriteTeam>())
            isFavorite = !favorite.isEmpty()
        }


//        if (isFavorite){
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
//        } else{
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
//        }
    }



    override fun showLoading() {
        showProgressDialog(this,"Loading")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun loadTeamBadge(url: String) {
        Glide.with(this).load(url).into(imgTeam)
    }

    override fun loadTeamName(teamName: String) {
        tvTeamName.text = teamName
    }

    override fun loadTeamYear(year: String) {
        tvTeamYear.text = year
    }

    override fun loadTeamStadion(teamStadion: String) {
        tvTeamStadion.text = teamStadion
    }

    private lateinit var pagerAdapter: TeamDetailPagerAdapter
    private val presenter: TeamDetailPresenter = TeamDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        if(intent.getBooleanExtra("fromFavorite",false)){
            team = Team()
            team.idLeague = intent.getStringExtra("idLeague")
            team.idTeam = intent.getStringExtra("idTeam")
            team.intFormedYear = intent.getStringExtra("intFormedYear")
            team.strDescriptionEN = intent.getStringExtra("strDescriptionEN")
            team.strStadium = intent.getStringExtra("strStadium")
            team.strTeam = intent.getStringExtra("strTeam")
            team.strTeamBadge = intent.getStringExtra("strTeamBadge")
        }else{
            team = intent.getSerializableExtra("team") as Team
        }
        supportActionBar?.title = team.strTeam

        if(team.strDescriptionEN!=null){
            overviewFragment = OverviewFragment.newInstance(team.strDescriptionEN!!)
        }else{
            overviewFragment = OverviewFragment.newInstance("No Overview")
        }

        playersFragment = PlayersFragment.newInstance(team.idTeam!!)

        supportFragmentManager.let {
            pagerAdapter = TeamDetailPagerAdapter(it,overviewFragment,playersFragment)
        }

        setUpPagerAdapter()

        presenter.loadData(team)
        presenter.setFavorite()
    }

    private fun setUpPagerAdapter() {
        tab_layout.addTab(tab_layout.newTab().setText("Overview"))
        tab_layout.addTab(tab_layout.newTab().setText("Players"))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        team_view_pager.adapter = pagerAdapter
        team_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { team_view_pager.currentItem = it.position }
                team_view_pager.currentItem = tab!!.position
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(isFavorite){
                    menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
                    removeFromFavorite(team)
                }else{
                    menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
                    addToFavorite(team)
                }
                isFavorite = !isFavorite
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
